// C++ include
#include <iostream>
#include <string>
#include <vector>

// Utilities for the Assignment
#include "raster.h"

#include <gif.h>
#include <fstream>

#include <Eigen/Geometry>
// Image writing library
#define STB_IMAGE_WRITE_IMPLEMENTATION // Do not include this line twice in your project!
#include "stb_image_write.h"

using namespace std;
using namespace Eigen;

//Image height
const int H = 480;

//Camera settings
const double near_plane = 1.5; //AKA focal length
const double far_plane = near_plane * 100;
const double field_of_view = 0.7854; //45 degrees
const double aspect_ratio = 1.5;
const bool is_perspective = true;// changed
const Vector3d camera_position(0, 0, 3);
const Vector3d camera_gaze(0, 0, -1);
const Vector3d camera_top(0, 1, 0);

//Object
const std::string data_dir = DATA_DIR;
const std::string mesh_filename(data_dir + "bunny.off");
MatrixXd vertices; // n x 3 matrix (n points)
MatrixXi facets;   // m x 3 matrix (m triangles)

//Material for the object
const Vector3d obj_diffuse_color(0.5, 0.5, 0.5);
const Vector3d obj_specular_color(0.2, 0.2, 0.2);
const double obj_specular_exponent = 256.0;

//Lights
std::vector<Vector3d> light_positions;
std::vector<Vector3d> light_colors;
//Ambient light
const Vector3d ambient_light(0.3, 0.3, 0.3);

//Fills the different arrays
void setup_scene()
{
    //Loads file
    std::ifstream in(mesh_filename);
    if (!in.good())
    {
        std::cerr << "Invalid file " << mesh_filename << std::endl;
        exit(1);
    }
    std::string token;
    in >> token;
    int nv, nf, ne;
    in >> nv >> nf >> ne;
    vertices.resize(nv, 3);
    facets.resize(nf, 3);
    for (int i = 0; i < nv; ++i)
    {
        in >> vertices(i, 0) >> vertices(i, 1) >> vertices(i, 2);
    }
    for (int i = 0; i < nf; ++i)
    {
        int s;
        in >> s >> facets(i, 0) >> facets(i, 1) >> facets(i, 2);
        assert(s == 3);
    }

    //Lights
    light_positions.emplace_back(8, 8, 0);
    light_colors.emplace_back(16, 16, 16);

    light_positions.emplace_back(6, -8, 0);
    light_colors.emplace_back(16, 16, 16);

    light_positions.emplace_back(4, 8, 0);
    light_colors.emplace_back(16, 16, 16);

    light_positions.emplace_back(2, -8, 0);
    light_colors.emplace_back(16, 16, 16);

    light_positions.emplace_back(0, 8, 0);
    light_colors.emplace_back(16, 16, 16);

    light_positions.emplace_back(-2, -8, 0);
    light_colors.emplace_back(16, 16, 16);

    light_positions.emplace_back(-4, 8, 0);
    light_colors.emplace_back(16, 16, 16);
}

void build_uniform(UniformAttributes &uniform)
{
    //TODO: setup uniform
    Eigen::Matrix<FrameBufferAttributes,Eigen::Dynamic,Eigen::Dynamic> frameBuffer(500,500);
    uniform.color << 1,1,1,1;

    //TODO: setup camera, compute w, u, v
    const Vector3d w = -camera_gaze.normalized();
    const Vector3d u = camera_top.cross(w).normalized();
    const Vector3d v = w.cross(u);

    //TODO: compute the camera transformation
    // matrix_c to get the light shot from camera
    Matrix4d Matrix_c;
    Matrix_c <<
	u(0), v(0), w(0), camera_position(0),
	u(1), v(1), w(1), camera_position(1),
	u(2), v(2), w(2), camera_position(2),
	0, 0, 0, 1;

	uniform.camera_transformation = Matrix_c.inverse();

    //TODO: setup projection matrix
	double f = -far_plane;
	double n = -near_plane;
	double t = -n * std::tan(field_of_view / 2.0);
	double r = frameBuffer.rows() / frameBuffer.cols() * t;
	double b = -t;
	double l = -r;
	
	uniform.orth_projection <<
	2 / (r - l), 0, 0, -(r + l) / (r - l),
	0, 2 / (t - b), 0, -(t + b) / (t - b),
	0, 0, 2 / (n - f), -(n + f) / (n - f),
	0, 0, 0, 1;

    
	Matrix4d Plane;
    if (is_perspective)
    {
        //TODO setup prespective camera
        
	    Plane << 
	    n, 0, 0, 0,
	    0, n, 0, 0,
	    0, 0, n + f, -(f * n),
	    0, 0, 1, 0;
        uniform.perspective = Plane;
    }
    else{
        Plane <<
        1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1;
    }
    uniform.view <<
    1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1;
    uniform.view(0,0) = 1/aspect_ratio;
    Matrix4d tmp = uniform.orth_projection *uniform.view* Plane *  uniform.camera_transformation;

    
    uniform.M = tmp.cast<float>();
    
}

void simple_render(Eigen::Matrix<FrameBufferAttributes, Eigen::Dynamic, Eigen::Dynamic> &frameBuffer)
{
    UniformAttributes uniform;
    build_uniform(uniform);
    Program program;
    // 
    program.VertexShader = [](const VertexAttributes &va, const UniformAttributes &uniform) {
        //TODO: fill the shader
        VertexAttributes out = va;
        out.position = uniform.M * va.position;
        
        return out;
    };

    program.FragmentShader = [](const VertexAttributes &va, const UniformAttributes &uniform) {
        //TODO: fill the shader
        return FragmentAttributes(1,0,0);
    };

    program.BlendingShader = [](const FragmentAttributes &fa, const FrameBufferAttributes &previous) {
        //TODO: fill the shader
        
        return FrameBufferAttributes(fa.color[0] * 255, fa.color[1] * 255, fa.color[2] * 255, fa.color[3] * 255);
    };

    std::vector<VertexAttributes> vertex_attributes;
    //TODO: build the vertex attributes from vertices and facets
    for(int i =0; i< facets.rows(); i++)
    {
        Vector3i facet = facets.row(i);
        Vector3d v1 = vertices.row(facet[0]);
        Vector3d v2 = vertices.row(facet[1]);
        Vector3d v3 = vertices.row(facet[2]);
        for(int j = 0; j <facet.size(); j++)
        {
            Vector3d vertex = vertices.row(facet[j]);
            vertex_attributes.push_back(VertexAttributes(vertex[0],vertex[1],vertex[2]));
        }
    }

    rasterize_triangles(program, uniform, vertex_attributes, frameBuffer);
}

Matrix4d compute_rotation(const double alpha)
{
    //TODO: Compute the rotation matrix of angle alpha on the y axis around the object barycenter
    Matrix4d result;

	double cosine = cos(alpha * M_PI / 180);
	double sine = sin(alpha* M_PI / 180);
	result <<
	cosine, 0, sine, 0,
	0, 1, 0, 0,
	-sine, 0, cosine, 0,
	0, 0, 0, 1;
    return result;
}

void wireframe_render(const double alpha, Eigen::Matrix<FrameBufferAttributes, Eigen::Dynamic, Eigen::Dynamic> &frameBuffer)
{
    UniformAttributes uniform;
    build_uniform(uniform);
    Program program;
    frameBuffer.setConstant(FrameBufferAttributes());

    Matrix4d trafo = compute_rotation(alpha);
    uniform.rotation_matrix = trafo;

    program.VertexShader = [](const VertexAttributes &va, const UniformAttributes &uniform) {
        //TODO: fill the shader, 
        // since the given data from main is double but I used float in raster 
        // then I need to cast double to float 
        VertexAttributes out = va;
        Matrix4f tmp_M = uniform.M.cast<float>();
        Matrix4f tmp_R = uniform.rotation_matrix.cast<float>();
        Matrix4f tmp;
        tmp<<
        1,0,0,0,
        0,1,0,0,
        0,0,1,0,
        0,0,0,1;
        out.position = uniform.M * tmp_R *tmp* va.position;
        return out;
    };

    program.FragmentShader = [](const VertexAttributes &va, const UniformAttributes &uniform) {
        //TODO: fill the shader
        return FragmentAttributes(1, 0, 0);
    };

    program.BlendingShader = [](const FragmentAttributes &fa, const FrameBufferAttributes &previous) {
        //TODO: fill the shader
        return FrameBufferAttributes(fa.color[0] * 255, fa.color[1] * 255, fa.color[2] * 255, fa.color[3] * 255);
    };

    std::vector<VertexAttributes> vertex_attributes;

    //TODO: generate the vertex attributes for the edges and rasterize the lines
    for(int i = 0; i< facets.rows();i++)
    {
        VectorXi facet = facets.row(i);
        for(int j = 0; j< facet.size(); j++)
        {
            VectorXd vertex1 = vertices.row(facet[j%facet.size()]);
            VectorXd vertex2 = vertices.row(facet[(j+1)%facet.size()]);
            vertex_attributes.push_back(VertexAttributes(vertex1[0],vertex1[1],vertex1[2]));
            vertex_attributes.push_back(VertexAttributes(vertex2[0],vertex2[1],vertex2[2]));
        }
    }
    //TODO: use the transformation matrix

    rasterize_lines(program, uniform, vertex_attributes, 0.5, frameBuffer);
}

void get_shading_program(Program &program)
{
    program.VertexShader = [](const VertexAttributes &va, const UniformAttributes &uniform) {
        //TODO: transform the position and the normal
        VertexAttributes out = va;
        Matrix4f tmp_R = uniform.rotation_matrix.cast<float>();
        Matrix4f tmp;
        tmp<<
        1,0,0,0,
        0,1,0,0,
        0,0,1,0,
        0,0,0,1;
        out.position = uniform.M *tmp_R* va.position;
        
        //TODO: compute the correct lighting
        Vector3d color = ambient_light;
        Vector3d hit(va.position(0),va.position(1),va.position(2));
        
        for(int i= 0; i<light_positions.size(); i++)
        {
            Vector3d Li = (light_positions[i] - hit). normalized();
            Vector3d N = va.normal;
            Vector3d diffuse = obj_diffuse_color*std::max(Li.dot(N),0.0);
            Vector3d H;
            if(is_perspective)
            {
                H =(Li +(camera_position - hit).normalized()).normalized();
            }
            else
            {
                H = (Li - camera_gaze.normalized()).normalized();
            }
            const Vector3d specular = obj_specular_color * std::pow(std::max(N.dot(H),0.0), obj_specular_exponent);
            const Vector3d D = light_positions[i] - hit;
            color+=(diffuse +specular).cwiseProduct(light_colors[i])/D.squaredNorm();
        }
        out.color = Vector4d(color(0),color(1),color(2),1);
        return out;
    };

    program.FragmentShader = [](const VertexAttributes &va, const UniformAttributes &uniform) {
        //TODO: create the correct fragment
        FragmentAttributes out;
        Vector3d color =ambient_light;
        Vector3d N = va.normal;
        Vector3d hit(va.position(0), va.position(1),va.position(2));
        for(int i= 0; i<light_positions.size(); i++)
        {
            Vector3d Li = (light_positions.at(i)-hit).normalized();
            
            Vector3d diffuse = obj_diffuse_color*std::max(Li.dot(N),0.0);
            Vector3d H;
            if(is_perspective)
            {
                H =(Li +(camera_position - hit).normalized()).normalized();
            }
            else
            {
                H = (Li - camera_gaze.normalized()).normalized();
            }
            const Vector3d specular = obj_specular_color * std::pow(std::max(N.dot(H),0.0), obj_specular_exponent);
            const Vector3d D = light_positions[i] - hit;
            color+=((diffuse +specular).cwiseProduct(light_colors[i]))/D.squaredNorm();
        }
        out= FragmentAttributes(color(0),color(1),color(2),1);
        if(is_perspective)
        {
            out.position = Vector4f(va.position(0),va.position(1),-va.position(2),va.position(3));
        }
        else
        {
            out.position = va.position;
        }

        return out;
    };

    program.BlendingShader = [](const FragmentAttributes &fa, const FrameBufferAttributes &previous) {
        //TODO: implement the depth check
        if(fa.position[2]> previous.depth)
        {
            FrameBufferAttributes out(fa.color[0]*255,fa.color[1]*255,fa.color[2]*255,fa.color[3]*255);
            out.depth = fa.position[2];
            return out;
        }
        else
        {
            return previous;
        }
        // return FrameBufferAttributes(fa.color[0] * 255, fa.color[1] * 255, fa.color[2] * 255, fa.color[3] * 255);
    };
}

void flat_shading(const double alpha, Eigen::Matrix<FrameBufferAttributes, Eigen::Dynamic, Eigen::Dynamic> &frameBuffer)
{
    UniformAttributes uniform;
    build_uniform(uniform);
    frameBuffer.setConstant(FrameBufferAttributes());
    Program program;
    get_shading_program(program);
    Eigen::Matrix4d trafo = compute_rotation(alpha);

    //rotation 
    uniform.rotation_matrix = trafo;

    std::vector<VertexAttributes> vertex_attributes;
    //TODO: compute the normals
    for(int i =0; i< facets.rows(); i++)
    {
        Vector3i facet = facets.row(i);
        Vector3d v1 = vertices.row(facet[0]);
        Vector3d v2 = vertices.row(facet[1]);
        Vector3d v3 = vertices.row(facet[2]);
        Vector3d normal= (v3-v2).cross(v1-v2).normalized();
        
        for(int j = 0; j <facet.size(); j++)
        {
            Vector3d vertex = vertices.row(facet[j]);
            VertexAttributes va(vertex[0],vertex[1],vertex[2],1,normal[0], normal[1],normal[2]);
            
            vertex_attributes.push_back(VertexAttributes(vertex[0],vertex[1],vertex[2],1,normal[0], normal[1],normal[2]));
        }
    }
    //TODO: set material colors
    rasterize_triangles(program, uniform, vertex_attributes, frameBuffer);
}

void pv_shading(const double alpha, Eigen::Matrix<FrameBufferAttributes, Eigen::Dynamic, Eigen::Dynamic> &frameBuffer)
{
    UniformAttributes uniform;
    build_uniform(uniform);
    frameBuffer.setConstant(FrameBufferAttributes());
    Program program;
    get_shading_program(program);

    Eigen::Matrix4d trafo = compute_rotation(alpha);

    //rotation 
    uniform.rotation_matrix = trafo;

    //TODO: compute the vertex normals as vertex normal average
    MatrixXd N = MatrixXd::Zero(vertices.rows(),3);
    for(int i = 0; i<facets.rows();i++)
    {
        Vector3i facet = facets.row(i);
        Vector3d v1 = vertices.row(facet[0]);
        Vector3d v2 = vertices.row(facet[1]);
        Vector3d v3 = vertices.row(facet[2]);
        Vector3d n_tmp = ((v3-v2).cross(v1- v2)).normalized();
        Vector3d normal = n_tmp;
        N.row(facet[2]) +=normal;

    }

    std::vector<VertexAttributes> vertex_attributes;
    //TODO: create vertex attributes
    for(int i = 0; i< facets.rows(); i++)
    {
        Vector3i facet = facets.row(i);
        for(int j = 0; j<facet.size(); j++)
        {
            Vector3d vertex = vertices.row(facet[j]);
            VectorXd normal = N.row(facet[j]).normalized();
            VertexAttributes va(vertex(0),vertex(1),vertex(2), 1,normal(0),normal(1),normal(2));
            vertex_attributes.push_back(va);
        }
    }
    //TODO: set material colors


    rasterize_triangles(program, uniform, vertex_attributes, frameBuffer);
}

void animation(int kind,  FrameBuffer& frameBuffer)
{
    vector<uint8_t> image;
    int delay = 25;
    double rotation_angle = 0;
    GifWriter g;
    if(kind == 1)
    {
        GifBegin(&g, "wireframe_gif.gif",frameBuffer.rows(),frameBuffer.cols(), delay);
    }
    else if(kind == 2)
    {
        GifBegin(&g, "flat_gif.gif",frameBuffer.rows(),frameBuffer.cols(), delay);
    }
    else if(kind == 3)
    {
        GifBegin(&g, "pv_gif.gif",frameBuffer.rows(),frameBuffer.cols(), delay);
    }
   

    for(int i =0; i<24; i++)
    {
        rotation_angle +=15;
        if(kind == 1)
        {
            wireframe_render(rotation_angle,frameBuffer);
        }
        else if(kind == 2)
        {
            flat_shading(rotation_angle,frameBuffer);
        }
        else if(kind == 3)
        {
            pv_shading(rotation_angle,frameBuffer);
        }
        framebuffer_to_uint8(frameBuffer,image);
        GifWriteFrame(&g,image.data(),frameBuffer.rows(),frameBuffer.cols(),delay);
    }
    GifEnd(&g);
}

int main(int argc, char *argv[])
{
    setup_scene();

    int W = H * aspect_ratio;
    Eigen::Matrix<FrameBufferAttributes, Eigen::Dynamic, Eigen::Dynamic> frameBuffer(W, H);
    vector<uint8_t> image;

    simple_render(frameBuffer);
    framebuffer_to_uint8(frameBuffer, image);
    stbi_write_png("simple.png", frameBuffer.rows(), frameBuffer.cols(), 4, image.data(), frameBuffer.rows() * 4);

    wireframe_render(0, frameBuffer);
    framebuffer_to_uint8(frameBuffer, image);
    stbi_write_png("wireframe.png", frameBuffer.rows(), frameBuffer.cols(), 4, image.data(), frameBuffer.rows() * 4);

    flat_shading(0, frameBuffer);
    framebuffer_to_uint8(frameBuffer, image);
    stbi_write_png("flat_shading.png", frameBuffer.rows(), frameBuffer.cols(), 4, image.data(), frameBuffer.rows() * 4);

    pv_shading(0, frameBuffer);
    framebuffer_to_uint8(frameBuffer, image);
    stbi_write_png("pv_shading.png", frameBuffer.rows(), frameBuffer.cols(), 4, image.data(), frameBuffer.rows() * 4);

    //TODO: add the animation
    animation(1,frameBuffer);
    animation(2,frameBuffer);
    animation(3,frameBuffer);
    return 0;
}

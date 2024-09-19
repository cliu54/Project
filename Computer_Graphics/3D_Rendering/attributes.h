#pragma once

#include <Eigen/Core>

class VertexAttributes
{
public:
    VertexAttributes(float x = 0, float y = 0, float z = 0, float w = 1, float nx =2, float ny = 0, float nz = 0)
    {
        position << x, y, z, w;
        color <<1,1,1,1;
        // new addidion: used for shadow computing 
        normal <<nx,ny,nz;

    }

    // Interpolates the vertex attributes
    static VertexAttributes interpolate(
        const VertexAttributes &a,
        const VertexAttributes &b,
        const VertexAttributes &c,
        const float alpha,
        const float beta,
        const float gamma)
    {
        VertexAttributes r;
        r.position = alpha * a.position + beta * b.position + gamma * c.position;
        r.normal = alpha * a.normal + beta * b.normal + gamma * c.normal;

        return r;
    }

    Eigen::Vector4f position;
    Eigen::Vector4d color;
    Eigen::Vector3d normal;
};

class FragmentAttributes
{
public:
    FragmentAttributes(float r = 0, float g = 0, float b = 0, float a = 1)
    {
        color << r, g, b, a;
    }

    Eigen::Vector4f color;
    Eigen::Vector4f position;
    float depth;
};

class FrameBufferAttributes
{
public:
    FrameBufferAttributes(uint8_t r = 0, uint8_t g = 0, uint8_t b = 0, uint8_t a = 255)
    {
        color << r, g, b, a;
        depth = -2;
    }

    Eigen::Matrix<uint8_t, 4, 1> color;
    float depth;
};
// set up for uniform
class UniformAttributes
{
    public:
    bool force_color = false;
	Eigen::Vector4d color; // rabbit color
	Eigen::Matrix4d translate_matrix;
	Eigen::Matrix4d camera_transformation;
	Eigen::Matrix4d orth_projection; // light reflection to camera
	Eigen::Matrix4f M;
    Eigen::Matrix4d view;
	Eigen::Matrix4d rotation_matrix;  // used for gif generation
	Eigen::Matrix4d perspective;
};
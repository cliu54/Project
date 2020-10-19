// Variables
var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");

    // create all objects
var player = new Move("soldier.png",0,200,32,44);
var block = new Move("block.png",100,368,32,32)
var enemy = new Move("enemy.png",500,350,50,50);
var landmine1= new Move("landmine.png", 250,380,32,20);
var landmine2 = new Move("landmine.png", 10,380,32,20);
var landmine3 = new Move("landmine.png", 350,380,32,20);
var landmine4 = new Move("landmine.png", 50,380,32,20);

    // all images here=
let background = new Image();
background.src = "background.jpg";

    // all boolean variables there
var isLeft = false;
var isRight = false;
var isUp = false;

    // physics variables
player.Weight = 1;

/* when user release keyboard, player stops moving*/
addEventListener("keyup",function(event)
{    
    if(String.fromCharCode(event.keyCode) == "D")
    {
        isRight = false
    }
    if(String.fromCharCode(event.keyCode) == "A")
    {
        isLeft = false;
    }

    if(String.fromCharCode(event.keyCode) == "W")
    {
        isUp = false;
    }
   
});

/* once pressed down keyboard triger movements*/
addEventListener("keydown",function(event)
{
    if(String.fromCharCode(event.keyCode) == "D")
    {
        isRight = true;
    }
    if(String.fromCharCode(event.keyCode) == "A")
    {
        isLeft = true;
    }

    if(String.fromCharCode(event.keyCode) == "W")
    {
        isUp = true;
    }
   
});

timer();
/*is to illustrate users' movement and draw the background*/
var onGround = false;

function draw()
{  
    if(Math.random() < 0.025)
    {
        for(var i = 0; i < bullets.length;i++)
        {
            if(bullets[i].active == false)
            {
                bullets[i].active = true;
            }
        }
    }
    player.X += player.Velocity_X;
    player.Y += player.Velocity_Y;
 
 // if statements to set player movements

    if(isLeft)
    {
        player.Velocity_X = -2;
    }

    if(isRight)
    {
        player.Velocity_X = 2;
    }
    
    if(!isLeft && !isRight)
    {
        player.Velocity_X = 0;
    }

    // when there is a player on canvas

    if(player.Y >= canvas.height - player.height)
    {    
        if(isUp && player.Velocity_Y == 0)
        {         
            player.Velocity_Y += -15;
         
        }

        // when player above the block and user press up button
        else if(isUp && !player.isColliding1(block))
        {
             player.Velocity_Y += -15;
        }

        // avoid glitchy to put player above the block and stop player's movements
        else
        { 
            player.Velocity_Y = 0;
            player.Y = canvas.height - player.height;
            onGround = true;
        }
    }
    
    // apply physics acceleration
    
    if(player.Velocity_Y < player.Weight)
    {
        player.Velocity_Y += player.Weight;
    }

// check when player is at the top of block
  if(player.isColliding1(block))
  {
    // Vertical 
    if(player.Y + player.height <= block.Y + player.Velocity_Y)
    {   
        // if player jumps on the block 
        if(isUp)
        {
            player.Velocity_Y += -10;
            player.Y += player.Velocity_Y;
        }

        // otherwise let player stay on the block
        else
        {
            player.Y = block.Y - player.height;
            player.Velocity_Y = 0;
        }
    }
    // Horizontal: use absoluate value distace to drag player back to the left or right side of block
    else if(player.X + player.width + player.Velocity_X > block.X )
    {   
        var left = Math.abs(player.X - block.X);
        var right = Math.abs(player.X - block.X - block.width);
        if(left<right)
        {
            player.X = block.X - player.width;
            player.Velocity_X = 0;
        }
        else
        {
            player.X = block.X + block.width;
            player.Velocity_X = 0;
        }    
    }
  }

  // the following methods are same as the first one
   if(player.isColliding2(enemy))
   {
    if(player.Y + player.height <= enemy.Y + player.Velocity_Y)
    {   
        player.Y = enemy.Y - player.height;
        player.Velocity_Y = 0;
        // onGround boolean turns true
        onGround = true;
    }
    
    else if(player.X + player.width + player.Velocity_X > enemy.X )
    {   
        var left = Math.abs(player.X - enemy.X);
        var right = Math.abs(player.X - enemy.X - enemy.width);
        if(left<right)
        {
            player.X = enemy.X - player.width;
            player.Velocity_X = 0;
        }
        else
        {
            player.X = enemy.X + enemy.width;
            player.Velocity_X = 0;
        }    
    }
  }
  if(player.isColliding3(landmine1))
   {
    if(player.Y + player.height <= landmine1.Y + player.Velocity_Y)
    {   
        player.Y = landmine1.Y - player.height;
        player.Velocity_Y = 0;
        onGround = true;
    }
    
    else if(player.X + player.width + player.Velocity_X > landmine1.X )
    {   
        var left = Math.abs(player.X - landmine1.X);
        var right = Math.abs(player.X - landmine1.X - landmine1.width);
        if(left<right)
        {
            player.X = landmine1.X - player.width;
            player.Velocity_X = 0;
        }
        else
        {
            player.X = landmine1.X + landmine1.width;
            player.Velocity_X = 0;
        }    
    }
  }
  if(player.isColliding4(landmine2))
   {
    if(player.Y + player.height <= landmine2.Y + player.Velocity_Y)
    {   
        player.Y = landmine2.Y - player.height;
        player.Velocity_Y = 0;
        onGround = true;
    }
    
    else if(player.X + player.width + player.Velocity_X > landmine2.X )
    {   
        var left = Math.abs(player.X - landmine2.X);
        var right = Math.abs(player.X - landmine2.X - landmine2.width);
        if(left<right)
        {
            player.X = landmine2.X - player.width;
            player.Velocity_X = 0;
        }
        else
        {
            player.X = landmine2.X + landmine2.width;
            player.Velocity_X = 0;
        }    
    }
  }
  
  if(player.isColliding5(landmine3))
   {
    if(player.Y + player.height <= landmine3.Y + player.Velocity_Y)
    {   
        player.Y = landmine3.Y - player.height;
        player.Velocity_Y = 0;
        onGround = true;
    }
    
    else if(player.X + player.width + player.Velocity_X > landmine3.X )
    {   
        var left = Math.abs(player.X - landmine3.X);
        var right = Math.abs(player.X - landmine3.X - landmine3.width);
        if(left<right)
        {
            player.X = landmine3.X - player.width;
            player.Velocity_X = 0;
        }
        else
        {
            player.X = landmine3.X + landmine3.width;
            player.Velocity_X = 0;
        }    
    }
  }
  
  if(player.isColliding6(landmine4))
   {
    if(player.Y + player.height <= landmine4.Y + player.Velocity_Y)
    {   
        player.Y = landmine4.Y - player.height;
        player.Velocity_Y = 0;
        onGround = true;
    }
    
    else if(player.X + player.width + player.Velocity_X > landmine4.X )
    {   
        var left = Math.abs(player.X - landmine4.X);
        var right = Math.abs(player.X - landmine4.X - landmine4.width);
        if(left<right)
        {
            player.X = landmine4.X - player.width;
            player.Velocity_X = 0;
        }
        else
        {
            player.X = landmine4.X + landmine4.width;
            player.Velocity_X = 0;
        }    
    }
  }
  // draw the background, player and obstacle
    ctx.clearRect(0,0,canvas.width,canvas.height);
    ctx.drawImage(background,0,0,600,400);
    ctx.drawImage(block.character, block.X, block.Y, block.width, block.height);
    ctx.drawImage(player.character,player.X,player.Y,player.width,player.height);
    ctx.drawImage(landmine1.character, landmine1.X, landmine1.Y, landmine1.width, landmine1.height);
    ctx.drawImage(landmine2.character, landmine2.X, landmine2.Y, landmine2.width, landmine2.height);
    ctx.drawImage(landmine3.character, landmine3.X, landmine3.Y, landmine3.width, landmine3.height);
    ctx.drawImage(landmine4.character, landmine4.X, landmine4.Y, landmine4.width, landmine4.height);

    ctx.drawImage(enemy.character, enemy.X, enemy.Y, enemy.width, enemy.height);
   drawbullet(); 
}


function drawbullet()
{
    // get all the bullets and check
    for(let i = 0; i < bullets.length; i++)
    {
        if(bullets[i].active)
        {
            bullets[i].x += bullets[i].vx;
            
            /*When bullets is going to pass through the block, change active property to false ,    
                so that bullets will stop as they reach the block.*/

            if(bullets[i].x < -10 || bullets[i].x <= block.X + block.width)
            {
                bullets[i]. active = false;
                bullets[i].x = enemy.X;
            }
            var playerleft = bullets[i].x > player.X ;
            var playerright = bullets[i].x < player.X + player.width - 10;
            var playerdown = bullets[i].y < player.Y + player.height;

         // messages
            if(onGround && playerleft && playerright && playerdown)
            {   
                alert("Game over");
                location.reload();
                return;
            }

            if(player.Y + player.height >= landmine1.Y)
            {   
                if (onGround && player.isColliding3(landmine1))
                {
                    alert("Game over");
                    location.reload();
                    return;
                }
            }
           
            if(player.Y + player.height >= landmine2.Y )
            {   
                if(onGround && player.isColliding4(landmine2))
                {
                    alert("Game over");
                    location.reload();
                    return;
                }
            }
            
            if(player.Y + player.height >= landmine3.Y)
            {
                if(onGround && player.isColliding5(landmine3))
                {
                    alert("Game over");
                    location.reload();
                    return;
                    
                }
            }  
            if(player.Y + player.height >= landmine4.Y)
            {
                if(onGround && player.isColliding6(landmine4))
                {
                    alert("Game over");
                    location.reload();
                    return;
                }
            }        
         // draw bullets   
            ctx.beginPath();
            ctx.arc(bullets[i].x,bullets[i].y, 4, 0, Math.PI * 2);
            ctx.fill();
            ctx.closePath();
        }  
        if(player.X + player.width >= canvas.width)
        {
            alert("You win!!");
            location.reload();
            return;
        }
    }
}

function timer()
{  
    var time = Math.floor(1000 / 60);
    setInterval(draw,time);
} 

/* the altributes of the objects( player or obstacle)*/
function Move(img,x,y,width,height)
{
this.character = new Image();
this.character.src = img;
this.X = x;
this.Y = y;
this.width = width;
this.height = height; 
this.Velocity_X = 0;
this.Velocity_Y = 0;
this.Weight = 0;


// check if player collides. return false when obstacle does not stop user.
this.isColliding1 = function (block)
{  var collided = false;
   var collidedX = false;
   var collidedY = false;
    
    // check if player collides the block
   
    if(player.X + player.width > block.X && player.X < block.X + block.width)
    {
        collidedX = true;
    }

     if(player.Y + player.height> block.Y && player.Y < block.Y + block.height)
    {
        collidedY = true;
    }
    collided = collidedX && collidedY;
    return collided;
}

this.isColliding2 = function (enemy)
{
    // check if player collides the block
    if(this.X > enemy.X + enemy.width)
    {
        return false;
    }
    
    if(this.X + this.width < enemy.X)
    {
        return false;
    }
    
    if(this.Y > enemy.height + enemy.y)
    {
        return false;
    }
    
    if(this.Y + this.height < enemy.Y)
    {
        return false;
    }
    
    else
    {
        return true;
    }
}
this.isColliding3 = function (landmine1)
{  var collided = false;
   var collidedX = false;
   var collidedY = false;
    
    // check if player collides the block
   
    if(player.X + player.width > landmine1.X && player.X < landmine1.X + landmine1.width)
    {
        collidedX = true;
    }

     if(player.Y + player.height >= landmine1.Y)
    {
        collidedY = true;
    }
    collided = collidedX && collidedY;
    return collided;
}

this.isColliding4 = function (landmine2)
{  var collided = false;
   var collidedX = false;
   var collidedY = false;
    
    // check if player collides the block
   
    if(player.X + player.width > landmine2.X && player.X < landmine2.X + landmine2.width)
    {
        collidedX = true;
    }

     if(player.Y + player.height >= landmine2.Y)
    {
        collidedY = true;
    }
    collided = collidedX && collidedY;
    return collided;
}
this.isColliding5 = function (landmine3)
{  var collided = false;
   var collidedX = false;
   var collidedY = false;
    
    // check if player collides the block
   
   if(player.X + player.width > landmine3.X && player.X < landmine3.X + landmine3.width)
    {
        collidedX = true;
    }

     if(player.Y + player.height >= landmine3.Y)
    {
        collidedY = true;
    }
    collided = collidedX && collidedY;
    return collided;
}

this.isColliding6 = function (landmine4)
{  var collided = false;
   var collidedX = false;
   var collidedY = false;
    
    // check if player collides the block
   
    if(player.X + player.width > landmine4.X && player.X < landmine4.X + landmine4.width)
    {
        collidedX = true;
    }

     if(player.Y + player.height >= landmine4.Y)
    {
        collidedY = true;
    }
    collided = collidedX && collidedY;
    return collided;
}
}

// bullets constructor 
var bullets = [];
for(var i = 0 ; i < 1000; i++)
{   
    bullets[i] = 
    {
        x: 400 - 50 * i,
        y: canvas.height - 15,
        vx: -1,
        active: false
    };
}














   
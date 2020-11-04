package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
/**
 *
 * @author ptfil
 */
public class RandomAnimationWithRange{
    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;
    Texture texture;
    SpriteBatch spriteBatch;
    Animation<TextureRegion> animation; // Must declare frame type (TextureRegion)
    float[] randomX;
    float[] randomY;
    int animNumber;
    float stateTime;
    float prevStateTime;
    float incrementalFrameTime;
    TextureRegion currentFrame;
    public void start(String textureName){
        // Load the sprite sheet as a Texture
        texture = new Texture(textureName);

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(texture,
                                                    texture.getWidth() / FRAME_COLS,
                                                    texture.getHeight() / FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
                for (int j = 0; j < FRAME_COLS; j++) {
                        frames[index++] = tmp[i][j];
                }
        }

        // Initialize the Animation with the frame interval and array of frames
        animation = new Animation<TextureRegion>(0.1f, frames);

        // Instantiate a SpriteBatch for drawing and reset the elapsed animation
        // time to 0
        spriteBatch = new SpriteBatch();
        randomX = new float[10];
        randomY = new float[10];
        stateTime = 0f;
        prevStateTime = 0f;
        incrementalFrameTime = 0f;
    }


    public void anim(int [] dimmArray, int animNumber){
        
        animNumber = animNumber;
        for(int i=0; i<animNumber; i++){
            randomX[i] = (float)getRandomIntegerRange(dimmArray[0],dimmArray[1]);
            randomY[i] = (float)getRandomIntegerRange(dimmArray[2],dimmArray[3]);
        }
        currentFrame = animation.getKeyFrame(incrementalFrameTime, true);

        if(stateTime%1.6f>incrementalFrameTime){
            incrementalFrameTime += 0.1f;
        }
        if (stateTime >= prevStateTime+1.6f){
            incrementalFrameTime = 0f;
            prevStateTime=stateTime;
        }
        spriteBatch.begin();
        for (int i=0; i<animNumber; i++){
            spriteBatch.draw(currentFrame, randomX[i], randomY[i]); // Draw current frame at (x,y)
        }
        spriteBatch.end(); 
    }
    private static double getRandomIntegerRange(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

}

package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.graphics.Texture;
public class RandomAnimActor extends Actor{
    private static final int FRAME_COLS = 4, FRAME_ROWS = 4;
    Texture texture;
    Animation<TextureRegion> animation;
    TextureRegion currentRegion;
    float time;
    float[] dimmArray;
    public RandomAnimActor(String textureName, int [] dimmArray) {
        dimmArray = dimmArray;
        setX((float)getRandomIntegerRange(dimmArray[0],dimmArray[1]));
        setY((float)getRandomIntegerRange(dimmArray[2],dimmArray[3]));
        // Load the sprite sheet as a Texture
        texture = new Texture("water-anim.png");

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
        Animation animation = new Animation<TextureRegion>(0.1f, frames);
        
        this.animation = animation;
    }

    @Override
    public void act(float delta){
        super.act(delta);
        time += delta;

        currentRegion = animation.getKeyFrame(time, true);
    }
    
    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        batch.draw(currentRegion, getX(), getY());
    }

    private static double getRandomIntegerRange(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }
}

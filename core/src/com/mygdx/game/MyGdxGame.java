package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MyGdxGame extends ApplicationAdapter {

        private static final int WAVE_NUMBER = 5, SINGLE_SIZE = 32;
	Stage stage;
	Texture landTexture;
        Texture waterTexture;
        RandomAnimActor[] anim;
	@Override
	public void create () {
            stage = new Stage(new ScreenViewport());
            drawBackground();
	}

	@Override
	public void render () {
            Gdx.gl.glClearColor(0, 0, 0, 1); //Clear screen
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.act();
            stage.draw();

	}
        
        private void drawBackground(){
            landTexture = new Texture("land1.png");
            Image landImg = new Image();
            for (int i = 0; i < Gdx.graphics.getWidth()/landImg.getWidth(); i++) {
                landImg = new Image(landTexture);
                landImg.setPosition(i * landImg.getWidth(), landImg.getHeight()*3);
                stage.addActor(landImg);
            }
            waterTexture =  new Texture("water1.png");
            Image waterImg = new Image(waterTexture);
            waterImg.setHeight(waterImg.getHeight()*3);
            waterImg.setWidth(Gdx.graphics.getWidth());
            waterImg.setPosition(0, 0);
            stage.addActor(waterImg);
            anim = new RandomAnimActor[WAVE_NUMBER];
            for (int i = 0; i < WAVE_NUMBER; i++) {
                anim[i] = new RandomAnimActor("water-anim.png", new int []{0,Gdx.graphics.getWidth(),0,SINGLE_SIZE*2});
                stage.addActor(anim[i]);
            }
        }


        @Override
	public void dispose() { // SpriteBatches and Textures must always be disposed
            landTexture.dispose();
            waterTexture.dispose();
            for (int i = 0; i < WAVE_NUMBER; i++) {
                anim[i].texture.dispose();
            }
	}
}

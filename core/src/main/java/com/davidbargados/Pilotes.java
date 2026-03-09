package com.davidbargados;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Pilotes extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;


    float posX,posY,velX,velY;

    float amplada;
    float altura;

    public FitViewport viewport;


    Texture pilotaTexture;

    @Override
    public void create() {
        batch = new SpriteBatch();
        viewport = new FitViewport(8,5);
        image = new Texture("libgdx.png");

        Pixmap pilotaPixmap = new Pixmap(100,100,Pixmap.Format.RGBA8888);
        pilotaPixmap.setColor(Color.RED);
        pilotaPixmap.fillCircle(50,50,25);
        pilotaTexture = new Texture(pilotaPixmap);
        posX = posY = 0f;
        velX = velY = 1f;
        amplada = viewport.getWorldWidth();
        altura = viewport.getWorldHeight();
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        float delta = Gdx.graphics.getDeltaTime();

        posX += velX * delta;
        posY += velY * delta;

        if (posY <= 0){
            posY = 0;
            velY *= -1;
        }

        if (posY >= altura - 1f){
            posY = altura - 1f;
            velY *= -1;
        }

        if (posX <= 0){
            posX = 0;
            velX *= -1;
        }

        if (posX >= amplada - 1f){
            posX = amplada - 1f;
            velX *= -1;
        }

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        batch.draw(pilotaTexture, posX, posY, 1f, 1f);
        batch.end();
    }

    // SUPER IMPORTANT, sense resize no funciona el Viewport
    // és un error típic oblidar aquest mètode, esteu avisats
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }
    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}

package io.piotrjastrzebski.androidsvgtest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SVGGame extends ApplicationAdapter {
	private static final String TAG = SVGGame.class.getSimpleName();
	SpriteBatch batch;
	Texture img;
	SVGBuilder builder;
	Texture texture;
	public SVGGame (SVGBuilder builder) {
		this.builder = builder;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		FileHandle cached = Gdx.files.external("acid1.png");
		if (cached.exists()) {
			Gdx.app.log(TAG, "Loaded Cached SVG");
			texture = new Texture(cached);
		} else {
			Pixmap pixmap = builder.loadSVG();
			if (pixmap != null) {
				Gdx.app.log(TAG, "Loaded SVG");
				texture = new Texture(pixmap);
				PixmapIO.writePNG(cached, pixmap);
				pixmap.dispose();
			} else {
				Gdx.app.log(TAG, "Failed to load SVG");
			}
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		if (texture != null) {
			batch.draw(texture, 0, 0);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		if (texture != null) {
			texture.dispose();
		}
	}

	public interface SVGBuilder {
		Pixmap loadSVG();
	}
}

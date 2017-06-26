package io.piotrjastrzebski.androidsvgtest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.caverock.androidsvg.SVG;
import com.caverock.androidsvg.SVGParseException;
import io.piotrjastrzebski.androidsvgtest.SVGGame;

import java.io.IOException;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		SVGGame.SVGBuilder builder = new SVGGame.SVGBuilder() {
			@Override public Pixmap loadSVG () {
				try {
					SVG svg = SVG.getFromAsset(AndroidLauncher.this.getAssets(), "acid1.svg");
					if (svg.getDocumentWidth() != -1) {
						Bitmap bitmap = Bitmap.createBitmap((int) Math.ceil(svg.getDocumentWidth()),
							(int) Math.ceil(svg.getDocumentHeight()),
							Bitmap.Config.ARGB_8888);
						Canvas canvas = new Canvas(bitmap);
						svg.renderToCanvas(canvas);

						Pixmap pixmap = new Pixmap(bitmap.getWidth(), bitmap.getHeight(), Pixmap.Format.RGBA8888);
						// i wonder why the colors are correct
						bitmap.copyPixelsToBuffer(pixmap.getPixels());
						bitmap.recycle();
						return pixmap;
					}
				} catch (SVGParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		};
		initialize(new SVGGame(builder), config);
	}
}

package io.piotrjastrzebski.androidsvgtest.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import io.piotrjastrzebski.androidsvgtest.SVGGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		SVGGame.SVGBuilder builder = new SVGGame.SVGBuilder() {
			@Override public Pixmap loadSVG () {
				return null;
			}
		};
		new LwjglApplication(new SVGGame(builder), config);
	}
}

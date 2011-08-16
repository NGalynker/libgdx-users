/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.gdxuser.util.DemoWrapper;

public class TestCollection extends DemoWrapper implements InputProcessor {
	
	private final DemoWrapper[] tests = {
			new PhysicsTest(), new IsoCamTest(), new DecalTest() };

	private int testIndex = 0;

	private Application app = null;

	@Override
	public void render () {
		tests[testIndex].render();
	}

	@Override
	public void create () {
		if (this.app == null) {
			this.app = Gdx.app;
			DemoWrapper test = tests[testIndex];
			test.create();
		}

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public boolean keyDown (int keycode) {
		if (keycode == Keys.SPACE) {
			app.log("TestCollection", "disposing test '" + tests[testIndex].getClass().getName());
			tests[testIndex].dispose();
			testIndex++;
			if (testIndex >= tests.length) testIndex = 0;
			DemoWrapper test = tests[testIndex];
			test.create();
			app.log("TestCollection", "created test '" + tests[testIndex].getClass().getName());
		} else {
			tests[testIndex].keyDown(keycode);
		}

		return false;
	}

	@Override
	public boolean keyTyped (char character) {
		tests[testIndex].keyTyped(character);
		return false;
	}

	@Override
	public boolean keyUp (int keycode) {
		tests[testIndex].keyUp(keycode);
		return false;
	}

	@Override
	public boolean touchDown (int x, int y, int pointer, int button) {
		tests[testIndex].touchDown(x, y, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) {
		tests[testIndex].touchDragged(x, y, pointer);
		return false;
	}

	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
		tests[testIndex].touchUp(x, y, pointer, button);
		return false;
	}

	@Override
	public boolean needsGL20 () {
		return false;
	}

	@Override
	public boolean touchMoved (int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled (int amount) {
		return false;
	}
}
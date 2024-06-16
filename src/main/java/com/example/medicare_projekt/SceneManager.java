package com.example.medicare_projekt;

import javafx.scene.Scene;

public class SceneManager {
    private static SceneManager instance;
    private Scene previousScene;

    private SceneManager() {}

    public static SceneManager getInstance() {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }

    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }

    public Scene getPreviousScene() {
        return previousScene;
    }
}


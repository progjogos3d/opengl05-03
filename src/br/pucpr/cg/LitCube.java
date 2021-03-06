package br.pucpr.cg;

import br.pucpr.mage.*;
import br.pucpr.mage.camera.Camera;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class LitCube implements Scene {
    private Keyboard keys = Keyboard.getInstance();

    private static final float SPEED = (float) Math.toRadians(180);

    private Shader shader;
    private Mesh mesh;
    private float angleX = 0.0f;
    private float angleY = 0.5f;
    private Camera camera = new Camera();

    @Override
    public void init() {
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        shader = Shader.loadProgram("phong");
        mesh = MeshFactory.createCube(shader);
        camera.getPosition().y = 1.0f;
    }

    @Override
    public void update(float secs) {
        if (keys.isPressed(GLFW_KEY_ESCAPE)) {
            glfwSetWindowShouldClose(glfwGetCurrentContext(), true);
            return;
        }

        if (keys.isDown(GLFW_KEY_A)) {
            angleY += SPEED * secs;
        } else if (keys.isDown(GLFW_KEY_D)) {
            angleY -= SPEED * secs;
        }

        if (keys.isDown(GLFW_KEY_W)) {
            angleX += SPEED * secs;
        } else if (keys.isDown(GLFW_KEY_S)) {
            angleX -= SPEED * secs;
        }
    }

    @Override
    public void draw() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        shader.bind();
            camera.apply(shader);
        shader
            //Luz
            .setUniform("uLightDir", new Vector3f(1.0f, -1.0f, -1.0f))
            .setUniform("uAmbientLight", new Vector3f(0.1f, 0.1f, 0.1f))   //Penumbra
            .setUniform("uDiffuseLight", new Vector3f(1.0f, 1.0f, 0.8f))   //Luz amarelada
            .setUniform("uSpecularLight", new Vector3f(1.0f, 1.0f, 1.0f))   //Luz amarelada
        .unbind();

        mesh
            //Transformação
            .setUniform("uWorld", new Matrix4f().rotateY(angleY).rotateX(angleX))
            //Maerial
            .setUniform("uAmbientMaterial", new Vector3f(0.5f, 0.0f, 0.5f))
            .setUniform("uDiffuseMaterial", new Vector3f(0.5f, 0.0f, 0.5f))
            .setUniform("uSpecularMaterial", new Vector3f(1.0f, 1.0f, 1.0f))
            .setUniform("uSpecularPower", 32.0f)
        .draw(shader);
    }

    @Override
    public void deinit() {
    }

    public static void main(String[] args) {
        new Window(new LitCube(), "Cube with lights", 800, 600).show();
    }
}
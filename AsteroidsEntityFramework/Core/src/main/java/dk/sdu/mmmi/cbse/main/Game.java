package dk.sdu.mmmi.cbse.main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dk.sdu.mmmi.cbse.asteroidsplittingsystem.AsteroidSplitter;
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidControlSystem;
import dk.sdu.mmmi.cbse.asteroidsystem.AsteroidPlugin;
import dk.sdu.mmmi.cbse.bulletsystem.BulletControlSystem;
import dk.sdu.mmmi.cbse.bulletsystem.BulletPlugin;
import dk.sdu.mmmi.cbse.collisiondetectionsystem.CollisionDetection;
import dk.sdu.mmmi.cbse.common.IShapeRender;
import dk.sdu.mmmi.cbse.common.MyShapeRender;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.enemysystem.EnemyControlSystem;
import dk.sdu.mmmi.cbse.enemysystem.EnemyPlugin;
import dk.sdu.mmmi.cbse.lifeprocessersystem.LifeProcesser;
import dk.sdu.mmmi.cbse.managers.GameInputProcessor;
import dk.sdu.mmmi.cbse.playersystem.PlayerControlSystem;
import dk.sdu.mmmi.cbse.playersystem.PlayerPlugin;

import java.util.ArrayList;
import java.util.List;

public class Game implements ApplicationListener {

    private final GameData gameData = new GameData();
    private final List<IEntityProcessingService> entityProcessors = new ArrayList<>();
    private final List<IPostEntityProcessingService> entityPostProcessors = new ArrayList<>();
    private final List<IGamePluginService> entityPlugins = new ArrayList<>();
    private final World world = new World();
    private IShapeRender sr;

    @Override
    public void create() {

        gameData.setDisplayWidth(Gdx.graphics.getWidth());
        gameData.setDisplayHeight(Gdx.graphics.getHeight());

        OrthographicCamera cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2f, gameData.getDisplayHeight() / 2f);
        cam.update();

        sr = new MyShapeRender(new ShapeRenderer());

        Gdx.input.setInputProcessor(new GameInputProcessor(gameData));

        entityPostProcessors.add(new CollisionDetection());
        entityPostProcessors.add(new LifeProcesser());
        entityPostProcessors.add(new AsteroidSplitter());

        entityPlugins.add(new PlayerPlugin());
        entityPlugins.add(new EnemyPlugin());
        entityPlugins.add(new BulletPlugin());
        entityPlugins.add(new AsteroidPlugin());

        entityProcessors.add(new PlayerControlSystem());
        entityProcessors.add(new AsteroidControlSystem());
        entityProcessors.add(new BulletControlSystem());
        entityProcessors.add(new EnemyControlSystem());

        for (IGamePluginService iGamePlugin : entityPlugins) {
            iGamePlugin.start(gameData, world);
        }
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gameData.setDelta(Gdx.graphics.getDeltaTime());

        update();

        draw();

        gameData.getKeys().update();
    }

    private void update() {
        for (IEntityProcessingService entityProcessorService : entityProcessors) {
            entityProcessorService.process(gameData, world);
        }

        for (IPostEntityProcessingService postEntityProcessingService : entityPostProcessors) {
            postEntityProcessingService.process(gameData, world);
        }
    }

    private void draw() {
        for (Entity entity : world.getEntities()) {
            entity.draw(sr);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}

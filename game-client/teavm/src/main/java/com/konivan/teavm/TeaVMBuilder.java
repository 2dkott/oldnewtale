package com.konivan.teavm;

import com.github.xpenatan.gdx.teavm.backends.shared.config.AssetFileHandle;
import com.github.xpenatan.gdx.teavm.backends.shared.config.compiler.TeaCompiler;
import com.github.xpenatan.gdx.teavm.backends.shared.config.plugin.TeaReflectionSupplier;
import com.github.xpenatan.gdx.teavm.backends.web.config.TeaBuildConfiguration;
import com.github.xpenatan.gdx.teavm.backends.web.config.TeaBuilder;
import com.github.xpenatan.gdx.teavm.backends.web.config.backend.WebBackend;

import java.io.File;
import java.io.IOException;

import org.teavm.tooling.TeaVMSourceFilePolicy;
import org.teavm.tooling.TeaVMTool;
import org.teavm.tooling.sources.DirectorySourceFileProvider;
import org.teavm.vm.TeaVMOptimizationLevel;

/**
 * Builds the TeaVM/HTML application.
 */
public class TeaVMBuilder {
    public static void main(String[] args) throws IOException {

        // Typically set by the Gradle task, but can also be set here or with the command-line arg "debug"
        boolean debug = false;
        // Typically set by the Gradle task, but can also be set here or with the command-line arg "run"
        boolean startJetty = false;
        for (String arg : args) {
            if ("debug".equals(arg)) debug = true;
            else if ("run".equals(arg)) startJetty = true;
        }

//        TeaBuildConfiguration teaBuildConfiguration = new TeaBuildConfiguration();
//        teaBuildConfiguration.assetsPath.add(new AssetFileHandle(".." + File.separatorChar + "assets"));
//        teaBuildConfiguration.webappPath = new File("build" + File.separatorChar + "dist").getCanonicalPath();
//
//        //libGDX
//        TeaReflectionSupplier.addReflectionClass("com.badlogic.gdx.math.Vector2");
//
//        //Artemis
//        TeaReflectionSupplier.addReflectionClass("com.artemis.BaseSystem");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.utils.BitVector");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.utils.Bag");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.Aspect.Builder");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.WildBag");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.EntityEdit");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.EntityTransmuter.TransmuteOperation");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentRemover");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.ComponentTypeFactory.ComponentTypeListener");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.EntitySubscription");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.Component");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.Aspect");
//        TeaReflectionSupplier.addReflectionClass("com.artemis.Entity");
//
//        //HyperLap2D Runtime
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.data");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.components");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.SceneLoader");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.systems");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.box2dLight.LightData");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.factory");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.editor.renderer.scripts.PhysicsBodyScript");
//
//        //HyperLap2D Extensions
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.h2d.extension.spine");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.h2d.extension.talos");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.talos.runtime.serialization");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.talos.runtime.modules");
//
//        //Game
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.component");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.system");
//        TeaReflectionSupplier.addReflectionClass("games.rednblack.hyperrunner.script");
//
//        TeaBuilder.config(teaBuildConfiguration);
//        TeaVMTool tool = new TeaVMTool();
//        tool.setOptimizationLevel(TeaVMOptimizationLevel.FULL);
//        tool.setObfuscated(true);
//        tool.setMainClass(TeaVMLauncher.class.getName());
//        TeaBuilder.build(tool, false);

//        new TeaCompiler(
//            new WebBackend()
//                .setHtmlWidth(800) // Change this to fit your game's requirements.
//                .setHtmlHeight(600) // Change this to fit your game's requirements.
//                .setHtmlTitle("old-tale-new-story")
//
////                .setWebAssembly(true) // Uncomment this line to use WASM output instead of JavaScript output.
//                .setStartJettyAfterBuild(startJetty)
//                .setJettyPort(8080)
//        ).build(new File("build/dist"));


        new TeaCompiler(
            new WebBackend()
                .setHtmlWidth(800) // Change this to fit your game's requirements.
                .setHtmlHeight(600) // Change this to fit your game's requirements.
                .setHtmlTitle("old-tale-new-story")
                .setStartJettyAfterBuild(startJetty)
                .setJettyPort(8080)
        )
            .addAssets(new AssetFileHandle("../assets"))
            .setOptimizationLevel(debug ? TeaVMOptimizationLevel.SIMPLE : TeaVMOptimizationLevel.ADVANCED)
            .setMainClass(TeaVMLauncher.class.getName())
            .setObfuscated(!debug)
            .setDebugInformationGenerated(debug)
            .setSourceMapsFileGenerated(debug)
            .setSourceFilePolicy(TeaVMSourceFilePolicy.COPY)
            .addSourceFileProvider(new DirectorySourceFileProvider(new File("../core/src/main/java/")))
            .addReflectionClass("com.artemis.BaseSystem")
            .addReflectionClass("com.artemis.utils.BitVector")
            .addReflectionClass("com.artemis.utils.Bag")
            .addReflectionClass("com.artemis.Aspect.Builder")
            .addReflectionClass("com.artemis.WildBag")
            .addReflectionClass("com.artemis.EntityEdit")
            .addReflectionClass("com.artemis.EntityTransmuter.TransmuteOperation")
            .addReflectionClass("com.artemis.ComponentRemover")
            .addReflectionClass("com.artemis.ComponentTypeFactory.ComponentTypeListener")
            .addReflectionClass("com.artemis.EntitySubscription")
            .addReflectionClass("com.artemis.Component")
            .addReflectionClass("com.artemis.Aspect")
            .addReflectionClass("com.artemis.Entity")
            .addReflectionClass("games.rednblack.editor.renderer.data.**")
            .addReflectionClass("games.rednblack.editor.renderer.components.**")
            .addReflectionClass("games.rednblack.editor.renderer.SceneLoader")
            .addReflectionClass("games.rednblack.editor.renderer.systems.**")
            .addReflectionClass("games.rednblack.editor.renderer.box2dLight.LightData")
            .addReflectionClass("games.rednblack.editor.renderer.factory.**")
            .addReflectionClass("games.rednblack.editor.renderer.scripts.PhysicsBodyScript")
            .addReflectionClass("games.rednblack.h2d.extension.spine.**")
            .addReflectionClass("games.rednblack.h2d.extension.talos.**")
            .addReflectionClass("games.rednblack.talos.runtime.serialization.**")
            .addReflectionClass("games.rednblack.talos.runtime.modules.**")
            .addReflectionClass("com.konivan.system.**")
            .build(new File("build/dist"));
    }
}

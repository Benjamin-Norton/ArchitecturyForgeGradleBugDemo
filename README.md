### [Demo Repository](https://github.com/Benjamin-Norton/ArchitecturyForgeGradleBugDemo)

Using a non-modding library, in this case "com.theokanning.openai-gpt3-java:[service|client|api]:0.11.0", is not present at runtime, despite being in the dependancy section of the forge and included as forgeRuntimeLibrary
<br>
This does not affect the fabric instance.

## Expected Result:
Minecraft forge launches w/ no `NoClassDefFoundError`

## Actual Result:
Forge fails to create mod instance.
<details>
<summary>Error log</summary>

```
 java.lang.reflect.InvocationTargetException: null
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) ~[?:?]
	at jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) ~[?:?]
	at jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) ~[?:?]
	at java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:499) ~[?:?]
	at java.lang.reflect.Constructor.newInstance(Constructor.java:480) ~[?:?]
	at net.minecraftforge.fml.javafmlmod.FMLModContainer.constructMod(FMLModContainer.java:68) ~[javafmllanguage-1.19.2-43.0.8.jar%23202!/:?]
	at net.minecraftforge.fml.ModContainer.lambda$buildTransitionHandler$10(ModContainer.java:121) ~[fmlcore-1.19.2-43.0.8.jar%23205!/:?]
	at java.util.concurrent.CompletableFuture$AsyncRun.run(CompletableFuture.java:1804) [?:?]
	at java.util.concurrent.CompletableFuture$AsyncRun.exec(CompletableFuture.java:1796) [?:?]
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:373) [?:?]
	at java.util.concurrent.ForkJoinPool$WorkQueue.topLevelExec(ForkJoinPool.java:1182) [?:?]
	at java.util.concurrent.ForkJoinPool.scan(ForkJoinPool.java:1655) [?:?]
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1622) [?:?]
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:165) [?:?]
Caused by: java.lang.NoClassDefFoundError: com/theokanning/openai/OpenAiApi
	at com.theokanning.openai.service.OpenAiService.buildApi(OpenAiService.java:239) ~[service-0.11.0.jar%23178!/:?]
	at com.theokanning.openai.service.OpenAiService.<init>(OpenAiService.java:66) ~[service-0.11.0.jar%23178!/:?]
	at com.theokanning.openai.service.OpenAiService.<init>(OpenAiService.java:56) ~[service-0.11.0.jar%23178!/:?]
	at net.examplemod.ExampleMod.init(ExampleMod.java:9) ~[16208733036397152962.jar%23209!/:?]
	at net.examplemod.forge.ExampleModForge.<init>(ExampleModForge.java:13) ~[%23206!/:?]
	... 14 more
Caused by: java.lang.ClassNotFoundException: com.theokanning.openai.OpenAiApi
	at cpw.mods.cl.ModuleClassLoader.loadClass(ModuleClassLoader.java:141) ~[securejarhandler-2.1.4.jar:?]
	at java.lang.ClassLoader.loadClass(ClassLoader.java:520) ~[?:?]
	at com.theokanning.openai.service.OpenAiService.buildApi(OpenAiService.java:239) ~[service-0.11.0.jar%23178!/:?]
	at com.theokanning.openai.service.OpenAiService.<init>(OpenAiService.java:66) ~[service-0.11.0.jar%23178!/:?]
	at com.theokanning.openai.service.OpenAiService.<init>(OpenAiService.java:56) ~[service-0.11.0.jar%23178!/:?]
	at net.examplemod.ExampleMod.init(ExampleMod.java:9) ~[16208733036397152962.jar%23209!/:?]
	at net.examplemod.forge.ExampleModForge.<init>(ExampleModForge.java:13) ~[%23206!/:?]
	... 14 more
```
</details>

#### Dependencies are present per `gradlew :forge:dependencies --configuration=forgeRuntimeLibrary`: 
<details>
<summary>Output</summary>

```
> Configure project :
Architect Plugin: 3.4.145
Architectury Loom: 0.12.0.301

> Task :forge:dependencies

------------------------------------------------------------
Project ':forge'
------------------------------------------------------------

forgeRuntimeLibrary
+--- cpw.mods:securejarhandler:2.1.4
|    +--- org.ow2.asm:asm:9.3
|    +--- org.ow2.asm:asm-tree:9.3
|    |    \--- org.ow2.asm:asm:9.3
|    \--- org.ow2.asm:asm-commons:9.3
|         +--- org.ow2.asm:asm:9.3
|         +--- org.ow2.asm:asm-tree:9.3 (*)
|         \--- org.ow2.asm:asm-analysis:9.3
|              \--- org.ow2.asm:asm-tree:9.3 (*)
+--- org.ow2.asm:asm:9.3
+--- org.ow2.asm:asm-commons:9.3 (*)
+--- org.ow2.asm:asm-tree:9.3 (*)
+--- org.ow2.asm:asm-util:9.3
|    +--- org.ow2.asm:asm:9.3
|    +--- org.ow2.asm:asm-tree:9.3 (*)
|    \--- org.ow2.asm:asm-analysis:9.3 (*)
+--- org.ow2.asm:asm-analysis:9.3 (*)
+--- net.minecraftforge:accesstransformers:8.0.4
|    +--- org.antlr:antlr4:4.9.1
|    |    +--- org.antlr:antlr4-runtime:4.9.1
|    |    +--- org.antlr:antlr-runtime:3.5.2
|    |    +--- org.antlr:ST4:4.3
|    |    |    \--- org.antlr:antlr-runtime:3.5.2
|    |    +--- org.abego.treelayout:org.abego.treelayout.core:1.0.3
|    |    +--- org.glassfish:javax.json:1.0.4
|    |    \--- com.ibm.icu:icu4j:61.1 -> 70.1
|    +--- org.antlr:antlr4-runtime:4.9.1
|    +--- net.sf.jopt-simple:jopt-simple:5.0.4
|    +--- org.ow2.asm:asm:9.1 -> 9.3
|    +--- org.ow2.asm:asm-commons:9.1 -> 9.3 (*)
|    +--- org.ow2.asm:asm-tree:9.1 -> 9.3 (*)
|    +--- org.apache.logging.log4j:log4j-api:2.11.+ -> 2.17.1
|    +--- org.apache.logging.log4j:log4j-core:2.11.+ -> 2.17.1
|    |    \--- org.apache.logging.log4j:log4j-api:2.17.1
|    \--- cpw.mods:modlauncher:9.0.4 -> 10.0.8
|         +--- org.ow2.asm:asm:9.3
|         +--- org.ow2.asm:asm-tree:9.3 (*)
|         +--- org.ow2.asm:asm-commons:9.3 (*)
|         +--- cpw.mods:securejarhandler:2.1.2 -> 2.1.4 (*)
|         +--- org.apache.logging.log4j:log4j-api:2.17.1
|         +--- org.apache.logging.log4j:log4j-core:2.17.1 (*)
|         \--- net.sf.jopt-simple:jopt-simple:5.0.4
+--- org.antlr:antlr4-runtime:4.9.1
+--- net.minecraftforge:eventbus:6.0.3
|    +--- net.jodah:typetools:0.8.+ -> 0.8.3
|    +--- net.sf.jopt-simple:jopt-simple:5.0.4
|    +--- org.ow2.asm:asm:9.2 -> 9.3
|    +--- org.ow2.asm:asm-commons:9.2 -> 9.3 (*)
|    +--- org.ow2.asm:asm-tree:9.2 -> 9.3 (*)
|    +--- org.apache.logging.log4j:log4j-api:2.17.1
|    \--- cpw.mods:modlauncher:10.0.+ -> 10.0.8 (*)
+--- net.minecraftforge:forgespi:6.0.0
|    +--- cpw.mods:modlauncher:9.0.7 -> 10.0.8 (*)
|    +--- org.ow2.asm:asm:9.2 -> 9.3
|    +--- org.ow2.asm:asm-commons:9.2 -> 9.3 (*)
|    +--- org.ow2.asm:asm-tree:9.2 -> 9.3 (*)
|    +--- org.apache.logging.log4j:log4j-api:2.17.0 -> 2.17.1
|    +--- org.apache.maven:maven-artifact:3.8.1 -> 3.8.5
|    |    +--- org.codehaus.plexus:plexus-utils:3.3.0
|    |    \--- org.apache.commons:commons-lang3:3.8.1 -> 3.12.0
|    \--- cpw.mods:securejarhandler:0.9.52 -> 2.1.4 (*)
+--- net.minecraftforge:coremods:5.0.1
|    +--- cpw.mods:modlauncher:9.0.+ -> 10.0.8 (*)
|    +--- org.apache.logging.log4j:log4j-api:2.14.1 -> 2.17.1
|    +--- org.ow2.asm:asm:9.1 -> 9.3
|    +--- org.ow2.asm:asm-commons:9.1 -> 9.3 (*)
|    +--- org.ow2.asm:asm-tree:9.1 -> 9.3 (*)
|    +--- org.ow2.asm:asm-util:9.1 -> 9.3 (*)
|    +--- net.minecraftforge:forgespi:4.0.+ -> 6.0.0 (*)
|    \--- org.openjdk.nashorn:nashorn-core:15.1.1 -> 15.3
|         +--- org.ow2.asm:asm:7.3.1 -> 9.3
|         +--- org.ow2.asm:asm-commons:7.3.1 -> 9.3 (*)
|         +--- org.ow2.asm:asm-tree:7.3.1 -> 9.3 (*)
|         \--- org.ow2.asm:asm-util:7.3.1 -> 9.3 (*)
+--- cpw.mods:modlauncher:10.0.8 (*)
+--- net.minecraftforge:unsafe:0.2.0
|    +--- org.apache.logging.log4j:log4j-api:2.11.+ -> 2.17.1
|    \--- org.apache.logging.log4j:log4j-core:2.11.+ -> 2.17.1 (*)
+--- com.electronwill.night-config:core:3.6.4
+--- com.electronwill.night-config:toml:3.6.4
|    \--- com.electronwill.night-config:core:3.6.4
+--- org.apache.maven:maven-artifact:3.8.5 (*)
+--- net.jodah:typetools:0.8.3
+--- net.minecrell:terminalconsoleappender:1.2.0
|    +--- org.apache.logging.log4j:log4j-core:2.8.1 -> 2.17.1 (*)
|    \--- org.jline:jline-reader:3.12.1
|         \--- org.jline:jline-terminal:3.12.1
+--- org.jline:jline-reader:3.12.1 (*)
+--- org.jline:jline-terminal:3.12.1
+--- dev.architectury:mixin-patched:0.8.5.+ -> 0.8.5.8
+--- org.openjdk.nashorn:nashorn-core:15.3 (*)
+--- net.minecraftforge:JarJarSelector:0.3.16
|    +--- net.minecraftforge:JarJarMetadata:0.3.16
|    |    +--- org.apache.maven:maven-artifact:3.8.1 -> 3.8.5 (*)
|    |    +--- com.google.code.gson:gson:2.8.5 -> 2.8.9
|    |    +--- com.google.guava:guava:31.0.1-jre
|    |    |    +--- com.google.guava:failureaccess:1.0.1
|    |    |    +--- com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava
|    |    |    +--- com.google.code.findbugs:jsr305:3.0.2
|    |    |    +--- org.checkerframework:checker-qual:3.12.0
|    |    |    +--- com.google.errorprone:error_prone_annotations:2.7.1
|    |    |    \--- com.google.j2objc:j2objc-annotations:1.3
|    |    +--- com.machinezoo.noexception:noexception:1.7.1
|    |    |    \--- org.slf4j:slf4j-api:1.7.30 -> 1.8.0-beta4
|    |    +--- org.slf4j:slf4j-api:1.7.30 -> 1.8.0-beta4
|    |    \--- org.slf4j:slf4j-simple:1.7.30
|    |         \--- org.slf4j:slf4j-api:1.7.30 -> 1.8.0-beta4
|    +--- org.apache.maven:maven-artifact:3.8.1 -> 3.8.5 (*)
|    +--- com.google.code.gson:gson:2.8.5 -> 2.8.9
|    +--- com.google.guava:guava:31.0.1-jre (*)
|    +--- com.machinezoo.noexception:noexception:1.7.1 (*)
|    +--- org.slf4j:slf4j-api:1.7.30 -> 1.8.0-beta4
|    \--- org.slf4j:slf4j-simple:1.7.30 (*)
+--- net.minecraftforge:JarJarMetadata:0.3.16 (*)
+--- cpw.mods:bootstraplauncher:1.1.2
|    \--- cpw.mods:securejarhandler:2.1.2 -> 2.1.4 (*)
+--- net.minecraftforge:JarJarFileSystems:0.3.16
+--- net.minecraftforge:fmlcore:1.19.2-43.0.8
|    +--- net.minecraftforge:eventbus:6.0.3 (*)
|    +--- net.minecraftforge:fmlloader:1.19.2-43.0.8
|    |    +--- org.ow2.asm:asm:9.3
|    |    +--- org.ow2.asm:asm-tree:9.3 (*)
|    |    +--- org.ow2.asm:asm-commons:9.3 (*)
|    |    +--- net.minecraftforge:forgespi:6.0.0 (*)
|    |    +--- org.apache.logging.log4j:log4j-api:2.17.0 -> 2.17.1
|    |    +--- org.slf4j:slf4j-api:1.8.0-beta4
|    |    +--- com.google.guava:guava:31.0.1-jre (*)
|    |    +--- com.google.code.gson:gson:2.8.9
|    |    +--- org.apache.maven:maven-artifact:3.8.5 (*)
|    |    +--- org.apache.commons:commons-lang3:3.12.0
|    |    +--- com.electronwill.night-config:core:3.6.4
|    |    +--- com.electronwill.night-config:toml:3.6.4 (*)
|    |    +--- cpw.mods:modlauncher:10.0.8 (*)
|    |    +--- net.minecraftforge:coremods:5.0.1 (*)
|    |    +--- com.mojang:logging:1.0.0
|    |    |    +--- org.apache.logging.log4j:log4j-slf4j18-impl:2.17.0
|    |    |    |    +--- org.slf4j:slf4j-api:1.8.0-beta4
|    |    |    |    +--- org.apache.logging.log4j:log4j-api:2.17.0 -> 2.17.1
|    |    |    |    \--- org.apache.logging.log4j:log4j-core:2.17.0 -> 2.17.1 (*)
|    |    |    +--- org.apache.logging.log4j:log4j-api:2.17.0 -> 2.17.1
|    |    |    \--- org.apache.logging.log4j:log4j-core:2.17.0 -> 2.17.1 (*)
|    |    +--- net.minecraftforge:JarJarSelector:0.3.16 (*)
|    |    +--- net.minecraftforge:JarJarMetadata:0.3.16 (*)
|    |    +--- net.sf.jopt-simple:jopt-simple:5.0.4
|    |    +--- cpw.mods:securejarhandler:2.1.4 (*)
|    |    +--- net.minecraftforge:accesstransformers:8.0.4 (*)
|    |    +--- net.minecrell:terminalconsoleappender:1.2.0 (*)
|    |    \--- org.apache.logging.log4j:log4j-core:2.17.0 -> 2.17.1 (*)
|    \--- commons-io:commons-io:2.11.0
+--- net.minecraftforge:fmlloader:1.19.2-43.0.8 (*)
+--- net.minecraftforge:javafmllanguage:1.19.2-43.0.8
|    +--- net.minecraftforge:fmlloader:1.19.2-43.0.8 (*)
|    \--- net.minecraftforge:fmlcore:1.19.2-43.0.8 (*)
+--- net.minecraftforge:lowcodelanguage:1.19.2-43.0.8
|    +--- net.minecraftforge:fmlloader:1.19.2-43.0.8 (*)
|    \--- net.minecraftforge:fmlcore:1.19.2-43.0.8 (*)
+--- net.minecraftforge:mclanguage:1.19.2-43.0.8
|    +--- net.minecraftforge:fmlloader:1.19.2-43.0.8 (*)
|    \--- net.minecraftforge:fmlcore:1.19.2-43.0.8 (*)
+--- dev.architectury:architectury-loom-runtime:1.1.3
+--- io.github.juuxel:unprotect:1.2.0
|    \--- org.apache.logging.log4j:log4j-api:2.11.2 -> 2.17.1


Here:
========================================================
+--- com.theokanning.openai-gpt3-java:service:0.11.0
+--- com.theokanning.openai-gpt3-java:api:0.11.0
+--- com.theokanning.openai-gpt3-java:client:0.11.0
+--- io.reactivex.rxjava2:rxjava:2.0.0
+--- com.squareup.retrofit2:retrofit:2.9.0
+--- com.squareup.retrofit2:adapter-rxjava2:2.9.0
+--- com.squareup.retrofit2:converter-jackson:2.9.0
+--- com.squareup.okhttp3:okhttp:3.14.9
+--- com.squareup.okio:okio:1.17.2
+--- com.fasterxml.jackson.core:jackson-annotations:2.10.1
+--- com.fasterxml.jackson.core:jackson-core:2.10.1
+--- com.fasterxml.jackson.core:jackson-databind:2.10.1
========================================================


+--- com.mojang:blocklist:1.0.10
+--- com.mojang:patchy:2.2.10
|    +--- com.google.guava:guava:31.0.1-jre (*)
|    \--- com.mojang:blocklist:1.0.10
+--- com.ibm.icu:icu4j:70.1
+--- commons-codec:commons-codec:1.15
+--- org.apache.commons:commons-compress:1.21
+--- org.apache.httpcomponents:httpclient:4.5.13
|    +--- org.apache.httpcomponents:httpcore:4.4.13 -> 4.4.14
|    +--- commons-logging:commons-logging:1.2
|    \--- commons-codec:commons-codec:1.11 -> 1.15
+--- commons-logging:commons-logging:1.2
+--- org.apache.httpcomponents:httpcore:4.4.14
+--- org.lwjgl:lwjgl:3.3.1
+--- org.lwjgl:lwjgl-jemalloc:3.3.1
|    \--- org.lwjgl:lwjgl:3.3.1
+--- org.lwjgl:lwjgl-openal:3.3.1
|    \--- org.lwjgl:lwjgl:3.3.1
+--- org.lwjgl:lwjgl-opengl:3.3.1
|    \--- org.lwjgl:lwjgl:3.3.1
+--- org.lwjgl:lwjgl-glfw:3.3.1
|    \--- org.lwjgl:lwjgl:3.3.1
+--- org.lwjgl:lwjgl-stb:3.3.1
|    \--- org.lwjgl:lwjgl:3.3.1
+--- org.lwjgl:lwjgl-tinyfd:3.3.1
|    \--- org.lwjgl:lwjgl:3.3.1
+--- com.mojang:text2speech:1.13.9
|    +--- net.java.dev.jna:jna:5.10.0
|    +--- com.google.guava:guava:31.0.1-jre (*)
|    +--- ca.weblite:java-objc-bridge:1.1
|    |    \--- net.java.dev.jna:jna:5.10.0
|    \--- org.slf4j:slf4j-api:1.8.0-beta4
+--- ca.weblite:java-objc-bridge:1.1 (*)
+--- net.minecraft:forge-1.19.2-43.0.8-minecraft-project-@forge-merged-named:1.19.2/loom.mappings.1_19_2.layered+hash.40359-v2-forge-1.19.2-43.0.8
+--- com.mojang:logging:1.0.0 (*)
+--- com.github.oshi:oshi-core:5.8.5
|    +--- net.java.dev.jna:jna:5.10.0
|    +--- net.java.dev.jna:jna-platform:5.10.0
|    |    \--- net.java.dev.jna:jna:5.10.0
|    \--- org.slf4j:slf4j-api:1.7.32 -> 1.8.0-beta4
+--- net.java.dev.jna:jna:5.10.0
+--- net.java.dev.jna:jna-platform:5.10.0 (*)
+--- org.slf4j:slf4j-api:1.8.0-beta4
+--- org.apache.logging.log4j:log4j-slf4j18-impl:2.17.0 (*)
+--- com.mojang:javabridge:1.2.24
+--- net.sf.jopt-simple:jopt-simple:5.0.4
+--- io.netty:netty-common:4.1.77.Final
+--- io.netty:netty-buffer:4.1.77.Final
|    \--- io.netty:netty-common:4.1.77.Final
+--- io.netty:netty-codec:4.1.77.Final
|    +--- io.netty:netty-common:4.1.77.Final
|    +--- io.netty:netty-buffer:4.1.77.Final (*)
|    \--- io.netty:netty-transport:4.1.77.Final
|         +--- io.netty:netty-common:4.1.77.Final
|         +--- io.netty:netty-buffer:4.1.77.Final (*)
|         \--- io.netty:netty-resolver:4.1.77.Final
|              \--- io.netty:netty-common:4.1.77.Final
+--- io.netty:netty-handler:4.1.77.Final
|    +--- io.netty:netty-common:4.1.77.Final
|    +--- io.netty:netty-resolver:4.1.77.Final (*)
|    +--- io.netty:netty-buffer:4.1.77.Final (*)
|    +--- io.netty:netty-transport:4.1.77.Final (*)
|    \--- io.netty:netty-codec:4.1.77.Final (*)
+--- io.netty:netty-resolver:4.1.77.Final (*)
+--- io.netty:netty-transport:4.1.77.Final (*)
+--- io.netty:netty-transport-native-unix-common:4.1.77.Final
|    +--- io.netty:netty-common:4.1.77.Final
|    +--- io.netty:netty-buffer:4.1.77.Final (*)
|    \--- io.netty:netty-transport:4.1.77.Final (*)
+--- io.netty:netty-transport-classes-epoll:4.1.77.Final
|    +--- io.netty:netty-common:4.1.77.Final
|    +--- io.netty:netty-buffer:4.1.77.Final (*)
|    +--- io.netty:netty-transport:4.1.77.Final (*)
|    \--- io.netty:netty-transport-native-unix-common:4.1.77.Final (*)
+--- com.google.guava:failureaccess:1.0.1
+--- com.google.guava:guava:31.0.1-jre (*)
+--- org.apache.commons:commons-lang3:3.12.0
+--- commons-io:commons-io:2.11.0
+--- com.mojang:brigadier:1.0.18
+--- com.mojang:datafixerupper:5.0.28
|    +--- com.google.code.gson:gson:2.8.9
|    +--- com.google.guava:guava:31.0.1-jre (*)
|    +--- it.unimi.dsi:fastutil:8.5.6
|    +--- com.google.code.findbugs:jsr305:3.0.2
|    +--- org.slf4j:slf4j-api:1.8.0-beta4
|    \--- org.apache.commons:commons-lang3:3.12.0
+--- com.google.code.gson:gson:2.8.9
+--- com.mojang:authlib:3.11.49
|    +--- com.google.code.findbugs:jsr305:3.0.2
|    +--- commons-io:commons-io:2.11.0
|    +--- com.google.code.gson:gson:2.8.8 -> 2.8.9
|    +--- org.apache.commons:commons-lang3:3.12.0
|    +--- com.google.guava:guava:31.0.1-jre (*)
|    \--- org.slf4j:slf4j-api:1.8.0-beta4
+--- it.unimi.dsi:fastutil:8.5.6
+--- org.apache.logging.log4j:log4j-api:2.17.0 -> 2.17.1
\--- org.apache.logging.log4j:log4j-core:2.17.0 -> 2.17.1 (*)

(*) - dependencies omitted (listed previously)

A web-based, searchable dependency report is available by adding the --scan option.

Deprecated Gradle features were used in this build, making it incompatible with Gradle 8.0.

You can use '--warning-mode all' to show the individual deprecation warnings and determine if they come from your own scripts or plugins.

See https://docs.gradle.org/7.4/userguide/command_line_interface.html#sec:command_line_warnings

BUILD SUCCESSFUL in 1s
1 actionable task: 1 executed
```
</details>

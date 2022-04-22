package cl.de_challenge;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.PCollection;


public class App {
    public static void main(String[] args){
        //PipelineOptionsFactory.register(cl.de_challenge.Options.class);

        PipelineOptionsFactory.register(Options.class);

        PipelineOptions pipelineOptions = PipelineOptionsFactory.create();
        //@TODO: Agregar opciones

        Options options = PipelineOptionsFactory.fromArgs(args).withValidation().as(Options.class);

        //Pipeline pipeline = Pipeline.create();
        Pipeline pipeline = Pipeline.create(options);


        //PCollection<String> data= pipeline.apply(TextIO.read().from("/Users/yanirasaez/Desktop/DE_CHALLENGE_MY/data/season-0910_json.json"));
        PCollection<String> data= pipeline.apply(TextIO.read().from(options.getInputFile()));


        data.apply(MapElements.via(
                new SimpleFunction<String, String>() {
                    @Override
                    public String apply(String line) {

                        System.out.println(line);
                        return line;
                    }

                }));

        // para crear un solo archivo con los datos que lei de los archivos de entrada, para eso es withNumShards
        // y el withSuffix es para que cree el archivo con una extension determinada
        //data.apply(TextIO.write().to("/Users/yanirasaez/Desktop/DE_CHALLENGE_MY/data/archivo2.json").withNumShards(1).withSuffix(".json"));
        data.apply(TextIO.write().to(options.getOutputFile()).withNumShards(1).withSuffix(options.getExtn()));


        //@TODO: Agregar pipeline a seguir
        pipeline.run().waitUntilFinish();

    }

}
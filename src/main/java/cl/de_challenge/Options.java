package cl.de_challenge;

import org.apache.beam.sdk.options.PipelineOptions;

public interface Options extends PipelineOptions {

    //@Description("origin path")
    //String getOriginPath();
    //void setOriginPath(String originPath);

    //@Description("destiny path")
    //String getDestinyPath();
    //void setDestinyPath(String destinyPath);

    void setInputFile(String file);
    String getInputFile();

    void setOutputFile(String file);
    String getOutputFile();

    void setExtn(String extn);
    String getExtn();

}

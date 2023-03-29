import jp.f_matano44.jfloatwavio.*;

import java.io.IOException;

import javax.sound.sampled.AudioFormat;

public class Test
{
    public static void main(String[] args)
    {
        AudioFormat inputFormat, outputFormat;
        WavIO input1=null, input2=null, output=null;
        double[] x, y;

        /* input wav file */
        // WavIO WavIO_Obj = new WavIO(String FILENAME);
        try{
            input1 = new WavIO("zundamon.wav");
            input2 = new WavIO("metan.wav");
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }


        /* get double array */
        // double[] signal = wavIO_Obj.getX();
        // float[]  signal = wavIO_Obj.getXf();
        x = input1.getX()[0];
        y = input2.getX()[0];

        /* draw original waveform */
        // new DrawSignal(String WindowTitle, double[]... signal);
        new DrawSignal("inputSignal 1", x);
        new DrawSignal("inputSignal 2", y);

        // connect input files
        inputFormat = input1.getFormat();
        outputFormat = new AudioFormat(
            AudioFormat.Encoding.PCM_SIGNED, inputFormat.getSampleRate(),
            inputFormat.getSampleSizeInBits(), 2, inputFormat.getFrameSize()*2,
            inputFormat.getFrameRate(), false
        );
        /* 
         * WavIO object can be generated from signal. 
         * If not supported format, throws Exception.
         * This call requires that the arrays of argument are same length.
         */
        // WavIO WavIO_Obj = new WavIO(AudioFormat format, double[]... signal);
        try{
            output = new WavIO(outputFormat, x, y);
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        // draw connected waveform
        new DrawSignal("outputSignal", x, y);

        /* print format */
        // WavIO_Obj.printAudioFormat();
        //output.printAudioFormat();

        /* output wav file */
        // wavIO_Obj.outputData(String FILENAME);
        try{
            input1.outputData("test1.wav");
            output.outputData("helloworld.wav");
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("Failed of writing file.");
        }
    }
}

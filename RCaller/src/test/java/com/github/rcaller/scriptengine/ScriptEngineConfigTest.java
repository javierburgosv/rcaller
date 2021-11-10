package com.github.rcaller.scriptengine;

import static org.junit.Assert.assertEquals;

import javax.script.ScriptException;

import com.github.rcaller.rstuff.RCallerOptions;

import org.junit.Test;

public class ScriptEngineConfigTest {

    public final String tempDir = "target/";
    private double delta = 1 / 100000;

    @Test
    public void init() throws ScriptException {

        RCallerOptions options = RCallerOptions.create(tempDir);
        RCallerScriptEngine engine = new RCallerScriptEngine(options);

        engine.eval("a <- 5");
        engine.eval("b <- 3");
        engine.eval("d <- a+b");
        double[] result = (double[]) engine.get("d");
        assertEquals(1, result.length);
        assertEquals(8.0, result[0], delta);

        engine.deleteTempFiles();
        engine.close();
    }
}

package com.om.dvr.fixture;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.joda.time.LocalTime;
import org.junit.Before;
import org.junit.Test;

public class CreateScheduleV2RowParserTest {
    private List<ProgramDescription> programDescriptors;

    @Before
    public void performParsing() {
        List<String> rows = buildRow();
        int channel = Integer.parseInt(rows.remove(0));
        LocalTime startTime = new LocalTime("1:00");

        V2RowParaser rowParser = new V2RowParaser(startTime, channel);
        programDescriptors = rowParser.parse(rows);
    }

    @Test
    public void assertNumberOfProgramDescriptorsIs20() {
        assertEquals(20, programDescriptors.size());
    }

    @Test
    public void assertFirstProgramName() {
        assertEquals("aaaa", programDescriptors.get(0).name);
    }

    @Test
    public void assertLastProgramName() {
        assertEquals("tt", programDescriptors.get(programDescriptors.size() - 1).name);
    }

    private List<String> buildRow() {
        List<String> row = new LinkedList<String>();
        String aRow = "345|aaaa|BBcc|cccc|ccDD|DDee|efff|ffff|fffg|gggg|gggh|hhii|jklm|nopq|rstt";
        for (String s : aRow.split("\\|"))
            row.add(s);
        return row;
    }

}

package cd27;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by ChenDeng
 * 2018-10-06 18:07
 */
public class TestAll {
    @Test
    public void testStringReverse() {
        System.out.println(StringReverse.reverse("chendeng"));
    }
    
    @Test
    public void testNowTimeDetail() {
        DateTimeUtil.getNowTimeDetail();
    }

    @Test
    public void testTimeMillis() {
        DateTimeUtil.getTimeMillis();
    }
    
    @Test
    public void testLastDay() {
        DateTimeUtil.getLastDay();
    }
    
    @Test
    public void testDateFormatter() {
        DateTimeUtil.formatDate();
    }
    
    @Test
    public void testYesterdayCurrent() {
        DateTimeUtil.yesterdayCurrent();
    }
    
    @Test
    public void testFileList() throws IOException {
        FileUtil.fileList();
    }

    @Test
    public void testShowFileList() throws IOException {
        FileUtil.showFileList();
    }
    
    @Test
    public void testShowDirectory() throws IOException {
        FileUtil.showDirectory(new File("F:\\Document"));
    }
}

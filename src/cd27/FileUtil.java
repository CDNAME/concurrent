package cd27;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by ChenDeng
 * 2018-10-06 20:22
 */
public final class FileUtil {
    private FileUtil() {
        throw new AssertionError();
    }
    
    public static void fileCopy(String source, String target) throws IOException {
        try(InputStream in = new FileInputStream(source)) {
            try(OutputStream out = new FileOutputStream(target)) {
                byte[] buffer = new byte[4096];
                int bytesToRead;
                while((bytesToRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
            }
        }
    }
    
    public static void fileCopyNIO(String source, String target) throws IOException {
        try(FileInputStream in = new FileInputStream(target)) {
            try(FileOutputStream out = new FileOutputStream(target)) {
                FileChannel inChannel = in.getChannel();
                FileChannel outChannel = out.getChannel();
                ByteBuffer buffer = ByteBuffer.allocate(4096);
                while(inChannel.read(buffer) != -1) {
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
            }
        }
    }
    
    public static int countWordInFile(String filename, String word) {
        int counter = 0;
        try(FileReader fr = new FileReader(filename)) {
            try(BufferedReader br = new BufferedReader(fr)) {
                String line = null;
                while((line = br.readLine()) != null) {
                    int index = -1;
                    while(line.length() >= word.length() && (index = line.indexOf(word)) >=0) {
                        counter++;
                        line = line.substring(index + word.length());
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return counter;
    }
    
    public static void fileList() {
        long start = System.currentTimeMillis();
        File f = new File("F:\\Document");
        for(File temp: f.listFiles()) {
            if(temp.isFile()) {
                System.out.println(temp.getName());
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    
    public static void showDirectory(File f) {
        _walkDirectory(f, 0);
    }
    
    public static void _walkDirectory(File f, int level) {
        if(f.isDirectory()) {
            for (File temp : f.listFiles()) {
                _walkDirectory(temp, level + 1);
            }
        } else {
            for(int i = 0; i < level-1; i++) {  //格式化输出
                System.out.print("---");
            }
            System.out.println(f.getName());
        }
    }
    
    public static void showFileList() throws IOException {
        long start = System.currentTimeMillis();
        Path initPath = Paths.get("F:\\Document");
        Files.walkFileTree(initPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file.getFileName().toString());
                return FileVisitResult.CONTINUE;
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}

package com.aifa.mins.ajbp.carchi.server.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import static com.google.zxing.client.j2se.MatrixToImageWriter.toBufferedImage;
import static org.apache.poi.hslf.record.RecordTypes.List;

@RestController
@RequestMapping("/zxing")
public class QCodeController {

    @RequestMapping("/getQ")
    public void getQCode(HttpServletResponse resp, String url) throws IOException {
        if (url != null && !"".equals(url)) {
            ServletOutputStream stream = null;
            try {
                int width = 200;//图片的宽度
                int height = 200;//高度
                stream = resp.getOutputStream();
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
                MatrixToImageWriter.writeToStream(m, "png", stream);
            } catch (WriterException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }
        }
    }

    @RequestMapping("/getBase64Q")
    public String getQCode(String url) throws IOException {
        String imageStr = "";
        if (url != null && !"".equals(url)) {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
                int width = 200;//图片的宽度
                int height = 200;//高度
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
                BufferedImage image = toBufferedImage(m);
                //注意此处拿到字节数据
                ImageIO.write(image, "png",outputStream);
                //Base64编码
                imageStr = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return imageStr;
    }

    public static void main(String[] args) {
        //传入目录和想要转换为小写的最后几位
        //doFileSomething("D:/tmp",false, 6);
        sort();
    }

    /**
     *
     * @param directory 文件目录
     * @param toUp 转化为大写传true
     * @param len 转换后几位
     */
    public static void doFileSomething(String directory, boolean toUp, int len) {
        List<String> fileNames = new ArrayList<>();
        findFileList(new File(directory), fileNames);
        for (int i = 0; i < fileNames.size(); i++) {
            File file = new File(fileNames.get(i));
            String pathFile = file.getAbsolutePath();
            int index = pathFile.lastIndexOf("\\");
            String oldPath = pathFile.substring(0, index + 1);
            String name = pathFile.substring(index + 1);
            String fileName = name.substring(0, name.lastIndexOf("."));
            String fileType = name.substring(name.lastIndexOf("."));
            //处理满足条件的文件
            if(fileName.length() >= len) {
                String preLenStr = fileName.substring(0, fileName.length() - len);
                String beLenStr = fileName.substring(fileName.length() - len);
                String newFileName = oldPath + preLenStr + (toUp?beLenStr.toUpperCase():beLenStr.toLowerCase()) + fileType;
                file.renameTo(new File(newFileName));
            } else {
                System.out.printf("%s，该文件长度不足%s位，无法转换\n", fileName, len);
            }
        }
    }

    public static void findFileList(File dir, List<String> fileNames) {
        // 判断是否存在目录
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 读取目录下的所有目录文件信息
        String[] files = dir.list();
        // 循环，添加文件名或回调自身
        for (int i = 0; i < files.length; i++) {
            File file = new File(dir, files[i]);
            if (file.isFile()) {
                // 如果文件，添加文件全路径名
                fileNames.add(dir + "\\" + file.getName());
            } else {
                // 如果是目录，回调自身继续查询
                findFileList(file, fileNames);
            }
        }
    }

    public static void writePic(String imgStr, String directory) {
        imgStr = "iVBORw0KGgoAAAANSUhEUgAAAMgAAADIAQAAAACFI5MzAAABoElEQVR42u2YW6rDMAxEDdqWQVsXaFsG3ZlxS9rA/bOgHylp2uQUHI1HDzrqv9d4yEN+hsQYVhnDl+O0LztI4shc4mtY6k4HCV7F9JjDw/dlE+ExDc9h1UqW17I1pyPoNkI4Tb5Y029anyO0RF6vm3fOEa08PKVn+C0XzpEYFgNaQkmoCVmntxBsVr0yC4LiEbJaSMxpWFmy4gzPZwuBPRgnFh/y/PJqIQovlVlwB3kPKQWozIJPoGh4C8kFRbkslAzlcLYQGX47Hvf1tYUwablddPz2SQ8pmt14mqyvtH0PYVdyrr1cpXxZCwmGCSO66jmPbCHFzifHBytf8KOFMLak41GTvhU9SrQoOxMiXRpWrIfYlhGxMr9ifGt9jOQ7p+R4/sJ6CJynoU5N6VPR02TS7Xyz/rFA9ZB3Q6LdefGp6EmyXiMx7AivjKtjnCW1p66irM5KkT2Ea8LxW06NXNZCUpvnoZ1TscgewrF7l1j2JfaMNqJ0Ykfn1l1ObCDI3WJFUir3kNfMYLtO3LQ+RzThb8fzAWj7FvL8F/CQXyZ/fdurRxJylJcAAAAASUVORK5CYII=";
        byte[] bytes = Base64.getDecoder().decode(imgStr);
        try {
            OutputStream out = new FileOutputStream(directory);
            out.write(bytes);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sort() {
        List<Integer> nums = Arrays.asList(10, 1, 18, 30, 23, 12, 7, 180, 5, 18, 17,10, 1, 18, 30, 23, 12, 7, 5, 18, 17);
        System.out.println("排序前：");
        nums.stream().forEach(x -> System.out.print(x + ","));
        //1、首先找出最大数
        int max = nums.stream().mapToInt(x -> x).summaryStatistics().getMax();
        System.out.printf("\n最大数为%d \n", max);
        int[] sortNums = new int[max + 1];
        for(int i=0; i< nums.size(); i++) {
            int index = nums.get(i);
            sortNums[index] = sortNums[index] + 1;
        }
        System.out.println("排序后：");
        BlockingQueue<Integer> integerBlockingQueue = new LinkedBlockingQueue<>();
        for(int i=0; i<=max; i++) {
            if(sortNums[i] != 0) {
                for(int j=0; j<sortNums[i]; j++) {
                    integerBlockingQueue.add(i);
                }
            }
        }
        integerBlockingQueue.forEach(x->{System.out.print(x + ",");});
    }
}

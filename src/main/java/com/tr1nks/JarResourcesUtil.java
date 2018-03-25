package com.tr1nks;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class JarResourcesUtil {
    private static final String RESOURCES_DIR_NAME = File.separator + "res" + File.separator;
    private static final String LIB_RESOURCES_DIR_NAME = File.separator + "lib" + File.separator;
    private static File unpackedResourceDir;
    private static OsType currentOs;

    /**
     * загрузить библиотеку
     * загружает библиотеку если она была ранее распакована, если не была то распакует и загрузит
     *
     * @param libName имя библиотеки (без расширения)
     */
    public static void unpackLoadLib(String libName) {
        String extLibName = libName + currentOs().getLibExtens();
        checkUnpackedResourceDir();
        File unpLibFile = new File(unpackedResourceDir.getPath() + LIB_RESOURCES_DIR_NAME + extLibName);
        if (!unpLibFile.exists()) {
            unpack(extLibName, unpLibFile);
        }
        System.load(unpLibFile.getAbsolutePath());
    }

    /**
     * распаковать ресурс
     * распакует ресурс в директорию ресурсов рядом с jar сохранив структуру директорий ресурса в проекте
     *
     * @param resName имя ресурса
     * @return файл распакованного ресурса
     */
    public static File unpack(String resName) {
        resName = resName.replace("\\", "/");
        if (!resName.startsWith("/")) {
            resName = "/" + resName;
        }
        checkUnpackedResourceDir();
        String unpFilePath = unpackedResourceDir.getPath() + File.separator;
        unpFilePath += resName.substring(1, resName.length()).replace("/", File.separator);
        System.out.println(unpFilePath);
        return unpack(resName, new File(unpFilePath));
    }

    /**
     * распаковать ресурс в указанный файл
     *
     * @param resName имя ресурса внутри jar
     * @param unpFile файл в который будет произведена распаковка
     * @return файл в который был распакован ресурс (unpFile)
     */
    public static File unpack(String resName, File unpFile) {
        new File(unpFile.getParent()).mkdirs();
        InputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            inputStream = JarResourcesUtil.class.getResourceAsStream("/" + resName);
            outputStream = new FileOutputStream(unpFile);
            int read;
            byte[] buffer = new byte[1024];
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != inputStream) {
                    inputStream.close();
                }
                if (null != outputStream) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return unpFile;
    }

    /**
     * проверяет на наличие или создает директорию ресурсов рядом с jar архивом
     */
    private static void checkUnpackedResourceDir() {
        if (null == unpackedResourceDir) {
            try {
                unpackedResourceDir = new File(new File(JarResourcesUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getPath() + RESOURCES_DIR_NAME);
                unpackedResourceDir.mkdirs();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * получить тип текущей системы
     *
     * @return тип текущей системы
     */
    private static OsType currentOs() {
        if (null == currentOs) {
            String osName = System.getProperty("os.name").toLowerCase();
            if (osName.contains("win")) {
                currentOs = OsType.WIN;
            } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
                currentOs = OsType.UNIX;
            } else if (osName.contains("mac")) {
                currentOs = OsType.MAC;
            } else {
                currentOs = null;
            }
        }
        return currentOs;
    }
}

/**
 * Тип операционной системы на которой сейчас запущен этот jar
 */
enum OsType {
    WIN(".dll"),
    UNIX(".so"),
    MAC(".dylib");
    private final String libExtens;

    /**
     * @param libExtens расширение библиотеки для конкретной системы
     */
    OsType(String libExtens) {
        this.libExtens = libExtens;
    }

    /**
     * получить расширение библиотек этой системы
     *
     * @return расширение библиотек (. включительно если необходима)
     */
    public String getLibExtens() {
        return libExtens;
    }
}

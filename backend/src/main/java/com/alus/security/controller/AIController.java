// AIController.java 수정
package com.alus.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@RestController
public class AIController {
    private final Logger logger = Logger.getLogger(AIController.class.getName());

    @GetMapping("/getAnswer")
    public String getAnswer(@RequestParam String question) {
        try {
            // Python 스크립트를 호출하여 질문을 인수로 전달
            ProcessBuilder pb = new ProcessBuilder(
                    "C:\\Users\\MSI\\anaconda3\\envs\\llama_env\\python.exe",
                    "C:\\Users\\MSI\\Downloads\\result.py",
                    question
            );
            logger.info("Running Python script with question: " + question);
            Process process = pb.start();

            // 에러 스트림 확인 및 출력
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            StringBuilder errorOutput = new StringBuilder();
            String errorLine;
            while ((errorLine = errorReader.readLine()) != null) {
                errorOutput.append(errorLine).append("\n");
            }
            if (errorOutput.length() > 0) {
                logger.warning("Python Error Output: " + errorOutput);
                return "Python 실행 오류: " + errorOutput.toString();
            }

            // Python 출력 결과 읽기
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            // 결과가 비어 있을 때의 처리
            if (result.length() == 0) {
                return "답변을 생성하지 못했습니다. 문제가 발생했습니다.";
            }

            return result.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Java 에러 발생: " + e.getMessage();
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleSocketClient {
    public static void main(String[] args) {
        final String serverAddress = "127.0.0.1"; // 서버 주소
        final int serverPort = 9999; // 서버 포트 번호

        try {
            // 서버에 연결
            Socket socket = new Socket(serverAddress, serverPort);
            System.out.println("서버에 연결되었습니다.");

            // 서버로 데이터를 보내는 PrintWriter 생성
            PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true);

            // 사용자로부터 입력 받기
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String messageToSend;

            do {
                System.out.print("서버에 보낼 메시지 입력 ('exit' 입력 시 종료): ");
                messageToSend = consoleReader.readLine();

                // 서버에 메시지 전송
                outputWriter.println(messageToSend);

                // 서버로부터의 응답 받기
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String receivedMessage = inputReader.readLine();
                System.out.println("서버로부터 받은 응답: " + receivedMessage);

            } while (!"exit".equals(messageToSend));

            // 자원 정리
            consoleReader.close();
            outputWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

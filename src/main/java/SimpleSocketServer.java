import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleSocketServer {
	public static void main(String[] args) {
		final int portNumber = 9999; // 포트 번호

		try {
			// 서버 소켓 생성
			ServerSocket serverSocket = new ServerSocket(portNumber);
			System.out.println("서버가 " + portNumber + " 포트에서 대기 중...");

			// 클라이언트의 연결을 기다림
			Socket clientSocket = serverSocket.accept();
			System.out.println("클라이언트가 연결되었습니다.");

			// read & write 객체 생성 ↓↓
			// 클라이언트로부터 데이터를 받는 BufferedReader 생성
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			// 클라이언트에 데이터를 보내는 PrintWriter 생성
			PrintWriter outputWriter = new PrintWriter(clientSocket.getOutputStream(), true);

			// 클라이언트로부터 메시지를 받아서 화면에 출력
			String receivedMessage;
			while ((receivedMessage = inputReader.readLine()) != null) {
				System.out.println("클라이언트로부터 받은 메시지: " + receivedMessage);

				// 클라이언트에 응답 메시지 전송
				String responseMessage = "서버가 메시지를 받았습니다.";
				outputWriter.println(responseMessage);

				// 특정 키 체크 ("exit" 입력 시 연결 종료)
				if ("exit".equals(receivedMessage)) {
					break;
				}
			}

			// 자원 정리
			inputReader.close();
			outputWriter.close();
			clientSocket.close();
			serverSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

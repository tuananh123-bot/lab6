package bai9;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Scanner;

public class AuctionClient extends UnicastRemoteObject implements AuctionClientCallback {

    protected AuctionClient() throws RemoteException {
        super();
    }

    @Override
    public void notifyNewBid(String productName, String bidderName, double newPrice) throws RemoteException {
        System.out.printf("\n[THÔNG BÁO THỜI GIAN THỰC] '%s' vừa được %s đặt mức giá cao nhất mới: %.2f\n",
                productName, bidderName, newPrice);
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            AuctionService service = (AuctionService) registry.lookup("AuctionService");

            AuctionClient callbackObj = new AuctionClient();
            service.registerClient(callbackObj);

            System.out.print("Nhập tên của bạn (Seller/Bidder): ");
            String username = sc.nextLine();

            while (true) {
                System.out.println("\n--- HỆ THỐNG ĐẤU GIÁ TRỰC TUYẾN ---");
                System.out.println("1. [Seller] Thêm sản phẩm đấu giá");
                System.out.println("2. [Bidder] Xem danh sách sản phẩm");
                System.out.println("3. [Bidder] Đặt giá thầu (Bid)");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 0) {
                    UnicastRemoteObject.unexportObject(callbackObj, true);
                    break;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên sản phẩm: ");
                        String prodName = sc.nextLine();
                        System.out.print("Nhập giá khởi điểm: ");
                        double startPrice = Double.parseDouble(sc.nextLine());
                        service.addProduct(prodName, startPrice);
                        System.out.println("Đã đăng ký sản phẩm thành công!");
                        break;
                    case 2:
                        List<String> products = service.getProducts();
                        if (products.isEmpty()) {
                            System.out.println("Hiện chưa có sản phẩm nào.");
                        } else {
                            products.forEach(System.out::println);
                        }
                        break;
                    case 3:
                        System.out.print("Nhập tên sản phẩm muốn đấu giá: ");
                        String bidProd = sc.nextLine();
                        System.out.print("Nhập mức giá thầu của bạn: ");
                        double bidPrice = Double.parseDouble(sc.nextLine());
                        if (service.placeBid(bidProd, username, bidPrice)) {
                            System.out.println("Đặt giá thầu thành công!");
                        } else {
                            System.out.println("Đặt giá thất bại (Sản phẩm không tồn tại hoặc giá thầu không cao hơn giá hiện tại).");
                        }
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

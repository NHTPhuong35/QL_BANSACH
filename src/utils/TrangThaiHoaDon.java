/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author nhatm
 */
public enum TrangThaiHoaDon {
    UNPAID(0, "Chưa thanh toán"),
    PAID(1, "Đã thanh toán"),
    CANCEL(2, "Bị hủy");
    
    private String name;
    private int id;
    
    TrangThaiHoaDon(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public static TrangThaiHoaDon getById(String id) {
//        for (TrangThaiHoaDon e : values()) {
//            if (e.id.equals(id)) {
//                return e;
//            }
//        }
//        return UNPAID;
//    }

//    public static TrangThaiHoaDon getByName(String name) {
//        for (TrangThaiHoaDon e : values()) {
//            if (e.name.equals(name)) {
//                return e;
//            }
//        }
//        return UNPAID;
//    }
}

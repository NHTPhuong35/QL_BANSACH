package BUS;

import DAO.QuyenDAO;
import DTO.ChiTietQuyenDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import GUI.HomeGUI;
import static GUI.HomeGUI.tkUSER;
import GUI.KhachHangGUI;
import GUI.LoginGUI;
import GUI.NhaCungCapGUI;
import GUI.PhanQuyenGUI;
import GUI.PhieuNhapGUI;
import GUI.BanHangGUI;
import GUI.HoaDonGUI;
import GUI.SanPhamGUI;
import GUI.TacGiaGUI;
import GUI.TaiKhoanDN;
import GUI.TaiKhoanGUI;
import GUI.TheLoaiGUI;
//import GUI.ThongTinHDGUI;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HomeBUS {
	
	private HomeGUI view;
        private PhieuNhapGUI phieunhapgui;
        private SanPhamGUI sanphamgui;
//        private ThongTinHDGUI thongtinhoadonguil;
        private TacGiaGUI tacgiagui;
        private NhaCungCapGUI nhacungcapgui;
        private TheLoaiGUI loaigui;
        private KhachHangGUI khachhanggui;
        private TaiKhoanGUI taikhoangui;
        private PhanQuyenGUI phanquyengui;
        private BanHangGUI salesgui;
        private HoaDonGUI hoadongui;
        
	public HomeBUS(HomeGUI view) {
            this.view = view;
            view.setVisible(true);
            addEvent();
	}
	private void addEvent() {
            boolean tacgia = false, loai = false, nhacungcap = false, khachhang = false, phieunhap = false, sanpham = false, hoadon = false
                    , thongke = false, taikhoan = false, phanquyen = false, banhang = false;
            boolean tacgiathem = false, tacgiasua = false, tacgiaxoa = false, loaithem = false, loaisua = false, loaixoa = false
                    , nhacungcapthem = false, nhacungcapsua = false, nhacungcapxoa = false, khachhangthem = false, khachhangsua = false
                    , phieunhapthem = false, phieunhapsua = false, phieunhapxoa = false, sanphamthem = false, sanphamsua = false, sanphamxoa = false, hoadonxoa = false
                    , taikhoanthem = false , taikhoansua = false , taikhoanxoa = false, phanquyenthem = false;
            QuyenDAO quyenDao = new QuyenDAO();
                ArrayList<ChiTietQuyenDTO> getChiTietQuyenByRole = quyenDao.getQuyenListByRole(view.getTkUser().getQuyen().getMaQuyen());
                for(ChiTietQuyenDTO chitietquyen : getChiTietQuyenByRole){
                    switch(chitietquyen.getMaChucNang()){
                        case "PN" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    phieunhap = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    phieunhapthem = true;
                                    break;
                                }
                                case "Sửa" ->{
                                    phieunhapsua = true;
                                    break;
                                }
                                case "Xóa" ->{
                                    phieunhapxoa = true;
                                    break;
                                }
                            }
                            break;
                        }
                        case "SP" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    sanpham = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    sanphamthem = true;
                                    break;
                                }
                                case "Sửa" ->{
                                    sanphamsua = true;
                                    break;
                                }
                                case "Xóa" ->{
                                    sanphamxoa = true;
                                    break;
                                }
                            }
                            break;
                        }
                        case "BH" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    banhang = true;
                                    
                                    break;
                                }
                            }
                            break;
                        }
                        case "HD" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    hoadon = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    break;
                                }
                                case "Sửa" ->{
                                    break;
                                }
                                case "Xóa" ->{
                                    hoadonxoa = true;
                                    break;
                                }
                            }
                            break;
                        }
                        case "TG" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    tacgia = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    tacgiathem = true;
                                    break;
                                }
                                case "Sửa" ->{
                                    tacgiasua = true;
                                    break;
                                }
                                case "Xóa" ->{
                                    tacgiaxoa = true;
                                    break;
                                }
                            }
                            break;
                        }
                        case "NCC" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    nhacungcap = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    nhacungcapthem = true;
                                    break;
                                }
                                case "Sửa" ->{
                                    nhacungcapsua = true;
                                    break;
                                }
                                case "Xóa" ->{
                                    nhacungcapxoa = true;
                                    break;
                                }
                            }
                            break;
                        }
                        case "LOAI" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    loai = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    loaithem = true;
                                    break;
                                }
                                case "Sửa" ->{
                                    loaisua = true;
                                    break;
                                }
                                case "Xóa" ->{
                                    loaixoa = true;
                                    break;
                                }
                            }
                            break;
                        }
                        case "KH" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    khachhang = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    khachhangthem = true;
                                    break;
                                }
                                case "Sửa" ->{
                                    khachhangsua = true;
                                    break;
                                }
                                case "Xóa" ->{
                                    break;
                                }
                            }
                            break;
                        }
                        case "TK" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    taikhoan = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    taikhoanthem = true;
                                    break;
                                }
                                case "Sửa" ->{
                                    taikhoansua = true;
                                    break;
                                }
                                case "Xóa" ->{
                                    taikhoanxoa = true;
                                    break;
                                }
                            }
                            break;
                        }
                        case "PQ" -> {
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    phanquyen = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    phanquyenthem = true;
                                    break;
                                }
                                case "Xóa" ->{
                                }
                            }
                            break;
                        }
                        case "THONGKE" -> {
                            thongke = true;
                            switch(chitietquyen.getHanhDong()){
                                case "Xem" -> {
                                    thongke = true;
                                    break;
                                }
                                case "Thêm" ->{
                                    break;
                                }
                                case "Sửa" ->{
                                    break;
                                }
                                case "Xóa" ->{
                                    break;
                                }
                            }
                            break;
                        }
                    }
                }
                
                GridBagConstraints menuPanelgbc = view.getmenuPanelgbc();
                JPanel menuPanel = view.getmenuPanel();
                                
                menuPanelgbc.gridy++;
                menuPanel.add(view.getBtnCaNhan(), menuPanelgbc);
                    
                if(phieunhap == true){
                    phieunhapgui = new PhieuNhapGUI();
                    JPanel toolBar = phieunhapgui.getToolBar();
                    if(phieunhapthem){
                        toolBar.add(phieunhapgui.getBtnThem());
                        phieunhapgui.revalidate();
                        phieunhapgui.repaint();
                    }
                    if(phieunhapsua){
                        toolBar.add(phieunhapgui.getBtnSua());
                        phieunhapgui.revalidate();
                        phieunhapgui.repaint();
                    }
                    if(phieunhapxoa){
                        toolBar.add(phieunhapgui.getBtnXoa());
                        phieunhapgui.revalidate();
                        phieunhapgui.repaint();
                    }
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnPhieuNhap(), menuPanelgbc);
                }
                if(sanpham == true){
                    sanphamgui = new SanPhamGUI();
                    JPanel pnHeader = sanphamgui.getpnHeader();
                    if(sanphamthem){
                        pnHeader.add(Box.createHorizontalStrut(20));
                        pnHeader.add(sanphamgui.getBtnThem());
                        sanphamgui.revalidate();
                        sanphamgui.repaint();

                    }
                    if(sanphamsua){
                        pnHeader.add(Box.createHorizontalStrut(20));
                        pnHeader.add(sanphamgui.getBtnSua());
                        sanphamgui.revalidate();
                        sanphamgui.repaint();

                    }
                    if(sanphamxoa){
                        pnHeader.add(Box.createHorizontalStrut(20));
                        pnHeader.add(sanphamgui.getBtnXoa());
                        sanphamgui.revalidate();
                        sanphamgui.repaint();
                    }
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnSanPham(), menuPanelgbc);
                }
                if(banhang == true){ 
//                    salesgui = new SalesGUI();
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnBanHang(), menuPanelgbc);
                }
                if(hoadon == true){
//                    ThongTinHDGUI thongtinhoadongui = new ThongTinHDGUI();
//                    JPanel pnThaoTac = thongtinhoadongui.getPnThaoTac();
//                    if(phieunhapthem){
//                    }
//                    if(phieunhapsua){
//                    }
//                    if(hoadonxoa){
//                        pnThaoTac.add(thongtinhoadongui.getBtnHuy());
//                        pnThaoTac.add(Box.createRigidArea(new Dimension(20, 0)));
//                        thongtinhoadongui.revalidate();
//                        thongtinhoadongui.repaint();
//;
//                    }
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnHoaDon(), menuPanelgbc);
                }
                if(tacgia == true){
                    tacgiagui = new TacGiaGUI();
                    JPanel pnBtn = tacgiagui.getPnBtn();
                    if(tacgiathem){
                        pnBtn.add(tacgiagui.getBtnThem());
                        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
                        tacgiagui.revalidate();
                        tacgiagui.repaint();
                    }
                    if(tacgiasua){
                        pnBtn.add(tacgiagui.getBtnSua());
                        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
                        tacgiagui.revalidate();
                        tacgiagui.repaint();
                    }
                    if(tacgiaxoa){
                        pnBtn.add(tacgiagui.getBtnXoa());
                        tacgiagui.revalidate();
                        tacgiagui.repaint();
                    }
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnTacGia(), menuPanelgbc);
                }
                if(nhacungcap == true){
                    nhacungcapgui = new NhaCungCapGUI();
                    JPanel pnBtn = nhacungcapgui.getPnBtn();
                    if(nhacungcapthem){
                        pnBtn.add(nhacungcapgui.getBtnThem());
                        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
                        nhacungcapgui.revalidate();
                        nhacungcapgui.repaint();
                    }
                    if(nhacungcapsua){
                        pnBtn.add(nhacungcapgui.getBtnSua());
                        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
                        nhacungcapgui.revalidate();
                        nhacungcapgui.repaint();
                    }
                    if(nhacungcapxoa){
                        pnBtn.add(nhacungcapgui.getBtnXoa());
                        nhacungcapgui.revalidate();
                        nhacungcapgui.repaint();
                    }
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnNhaCungCap(), menuPanelgbc);
                }
                if(loai == true){
                    loaigui = new TheLoaiGUI();
                    JPanel pnBtn = loaigui.getPnBtn();
                    if(loaithem){
                        pnBtn.add(loaigui.getBtnThem());
                        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
                        loaigui.revalidate();
                        loaigui.repaint();
                    }
                    if(loaisua){
                        pnBtn.add(loaigui.getBtnSua());
                        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
                        loaigui.revalidate();
                        loaigui.repaint();
                    }
                    if(loaixoa){
                        pnBtn.add(loaigui.getBtnXoa());
                        loaigui.revalidate();
                        loaigui.repaint();
                    }
                    
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnLoai(), menuPanelgbc);
                }
                if(khachhang == true){
                    khachhanggui = new KhachHangGUI();
                    JPanel pnBtn = khachhanggui.getPnBtn();
                    if(khachhangthem){
//                        pnBtn.add(khachhanggui.getBtnthem());
                        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));

//                        pnBtn.add(khachhanggui.getBtAdd());
//                        pnBtn.add(Box.createRigidArea(new Dimension(20, 0)));
                        khachhanggui.revalidate();
                        khachhanggui.repaint();
                    }
                    if(khachhangsua){

//                        pnBtn.add(khachhanggui.getBtnSua());
                        pnBtn.add(khachhanggui.getBtEdit());
                    }
                    
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnKhachHang(), menuPanelgbc);
                }
                if(taikhoan == true){          
                    taikhoangui = new TaiKhoanGUI(tkUSER);
                    JPanel pnThaoTac = taikhoangui.getPnThaoTac();
                    if(taikhoanthem){
                        pnThaoTac.add(taikhoangui.getBtnThem());
                        pnThaoTac.add(Box.createHorizontalStrut(20));
                        taikhoangui.revalidate();
                        taikhoangui.repaint();
                    }
                    if(taikhoansua){
                        pnThaoTac.add(taikhoangui.getBtnSua());
                        pnThaoTac.add(Box.createHorizontalStrut(20));
                        taikhoangui.revalidate();
                        taikhoangui.repaint();
                    }
                    if(taikhoanxoa){
                        pnThaoTac.add(taikhoangui.getBtnXoa());
                        pnThaoTac.add(Box.createHorizontalGlue());
                        taikhoangui.revalidate();
                        taikhoangui.repaint();
                    }

                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnTaiKhoan(), menuPanelgbc);
                }
                if(phanquyen == true){
                    phanquyengui = new PhanQuyenGUI();
                    JPanel pnHeadPanel = phanquyengui.getHeadPanel();
                    GridBagConstraints headPanelgbc = phanquyengui.getHeadPanelgbc();
                    
                    if(phanquyenthem){
                        headPanelgbc.gridx = 0;
                        headPanelgbc.gridy = 0;
                        headPanelgbc.weightx = 1;
                        headPanelgbc.anchor = GridBagConstraints.WEST;
                        headPanelgbc.insets = new Insets(0, 25, 0, 0); // Margin top left bottom right
                        pnHeadPanel.add(phanquyengui.getBtnThemQuyen(),headPanelgbc);
                        
                        phanquyengui.revalidate();
                        phanquyengui.repaint();
                    }
//                    if(taikhoansua){
//                        pnThaoTac.add(phanquyengui.getBtnSua());
//                        pnThaoTac.add(Box.createHorizontalStrut(20));
//                    }
//                    if(taikhoanxoa){
//                        pnThaoTac.add(phanquyengui.getBtnXoa());
//                        pnThaoTac.add(Box.createHorizontalGlue());
//                    }
                    
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnPhanQuyen(), menuPanelgbc);
                }
                if(thongke == true){
                    menuPanelgbc.gridy++;
                    menuPanel.add(view.getBtnThongKe(), menuPanelgbc);
                }
                
		view.getBtnLogout().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				int confirm = JOptionPane.showConfirmDialog(view, "Bạn thực sự muốn đăng xuất?");
				if (confirm != JOptionPane.YES_OPTION) {
					return;
				}
				view.dispose();
				new LoginBUS(new LoginGUI());
			}
		});
                
                view.getBtnCaNhan().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt){
                        TaiKhoanDN tkDN = new TaiKhoanDN(tkUSER);
                        view.getShowPanel().removeAll();
                        view.getShowPanel().add(tkDN, BorderLayout.CENTER);
                        view.getShowPanel().revalidate();
                        view.getShowPanel().repaint();
                    }
                });
                
                view.getBtnSanPham().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(sanphamgui, BorderLayout.CENTER);
                            sanphamgui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                view.getBtnKhachHang().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(khachhanggui, BorderLayout.CENTER);
                            khachhanggui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                view.getBtnKhachHang().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(khachhanggui, BorderLayout.CENTER);
                            khachhanggui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                view.getBtnTacGia().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(tacgiagui, BorderLayout.CENTER);
                            tacgiagui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                view.getBtnPhanQuyen().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(phanquyengui, BorderLayout.CENTER);
                            phanquyengui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                view.getBtnLoai().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(loaigui, BorderLayout.CENTER);
                            loaigui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                view.getBtnNhaCungCap().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(nhacungcapgui, BorderLayout.CENTER);
                            nhacungcapgui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                view.getBtnTaiKhoan().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(taikhoangui, BorderLayout.CENTER);
                            taikhoangui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                view.getBtnPhieuNhap().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
                            view.getShowPanel().removeAll();
                            view.getShowPanel().add(phieunhapgui, BorderLayout.CENTER);
                            phieunhapgui.setVisible(true);

                            view.getShowPanel().revalidate();
                            view.getShowPanel().repaint();
			}
		});
                
                
	}

}

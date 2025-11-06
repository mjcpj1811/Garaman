USE garaman;
SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE tblServiceRequest;
TRUNCATE TABLE tblSparePartRequest;
TRUNCATE TABLE tblRequest;
TRUNCATE TABLE tblInvoice;
TRUNCATE TABLE tblAppointment;
TRUNCATE TABLE tblService;
TRUNCATE TABLE tblSparePartReceiptDetail;
TRUNCATE TABLE tblSparePartReceipt;
TRUNCATE TABLE tblSparePart;
TRUNCATE TABLE tblSupplier;
TRUNCATE TABLE tblTechnician;
TRUNCATE TABLE tblStaff;
TRUNCATE TABLE tblMember;

-- ===================================================
-- 1️⃣ DỮ LIỆU NHÂN SỰ
-- ===================================================
INSERT INTO tblMember (name, username, password, birthday, address, email, phoneNumber, role, gender) VALUES
                                                                                                          ('Nguyễn Văn A', 'test', '1', '1995-05-10', 'Hà Nội', 'a.nv@example.com', '0901233567', 'Customer', 'Male'),
                                                                                                          ('Trần Thị B', 'sales_b', '1', '1990-11-20', 'Đà Nẵng', 'b.tt@example.com', '0912345678', 'Staff', 'Female'),
                                                                                                          ('Lê Văn C', 'warehouse_c', '1', '1985-01-15', 'Hải Phòng', 'c.lv@example.com', '0923456789', 'Staff', 'Male'),
                                                                                                          ('Phạm Minh D', 'technician_d', '1', '1992-07-25', 'Cần Thơ', 'd.pm@example.com', '0934567890', 'Staff', 'Male'),
                                                                                                          ('Hoàng Hải E', 'customer_e', '1', '2000-03-01', 'TP.HCM', 'e.hh@example.com', '0907654321', 'Customer', 'Male'),
                                                                                                          ('Phan Trọng G', 'customer_g', '1', '1988-02-29', 'Huế', 'g.pt@example.com', '0961122334', 'Customer', 'Male'),
                                                                                                          ('Đỗ Thanh H', 'customer_h', '1', '1998-12-12', 'Nha Trang', 'h.dt@example.com', '0975544332', 'Customer', 'Female'),
                                                                                                          ('Bùi Kiều K', 'customer_k', '1', '1993-06-05', 'Biên Hòa', 'k.bk@example.com', '0989988776', 'Customer', 'Female'),
                                                                                                          ('Ngô Thúy L', 'customer_l', '1', '1991-09-17', 'Tây Ninh', 'l.nt@example.com', '0919998887', 'Customer', 'Female'),
                                                                                                          ('Trần Văn M', 'customer_m', '1', '1983-04-03', 'Vũng Tàu', 'm.tv@example.com', '0928887776', 'Customer', 'Male'),
                                                                                                          ('Trương Nam Khánh', 'khanh', '1', '2004-11-18', 'Hải Phòng', 'khanh@gmail.com', '0385183985', 'Staff', 'Male'),
                                                                                                          ('Nguyễn Thị N', 'sales_n', '1', '1996-10-02', 'Hải Dương', 'n.nt@example.com', '0932349999', 'Staff', 'Female'),
                                                                                                          ('Vũ Thanh O', 'sales_o', '1', '1998-07-11', 'Bắc Ninh', 'o.vt@example.com', '0978988988', 'Staff', 'Male');

INSERT INTO tblStaff (tblMemberId, position) VALUES
                                                 (2, 'Sales'), (3, 'Warehouse'), (4, 'Technician'),
                                                 (11, 'Manager'), (12, 'Sales'), (13, 'Sales');

INSERT INTO tblTechnician (tblStaffId, skills) VALUES
    (4, 'Engine Repair, Suspension, Brakes');

-- ===================================================
-- 2️⃣ NHÀ CUNG CẤP / PHỤ TÙNG / DỊCH VỤ
-- ===================================================
INSERT INTO tblSupplier (name, address, phoneNumber, email) VALUES
                                                                ('NCC A', 'KCN Bắc Hà', '0941231231', 'ncc_a@supp.com'),
                                                                ('NCC B', 'KCN Nam Đà', '0952342342', 'ncc_b@supp.com');

INSERT INTO tblSparePart (name, description, stockQuantity, purchasePrice, sellingPrice) VALUES
                                                                                             ('Lọc dầu', 'Lọc dầu động cơ xe hơi', 50, 50000, 80000),
                                                                                             ('Má phanh', 'Bộ má phanh trước', 30, 400000, 650000),
                                                                                             ('Bugi', 'Bugi xe máy/ô tô', 100, 20000, 35000),
                                                                                             ('Dây curoa', 'Dây curoa động cơ', 20, 100000, 150000);

INSERT INTO tblService (name, description, price) VALUES
                                                      ('Thay nhớt', 'Thay dầu định kỳ', 300000),
                                                      ('Sửa phanh', 'Kiểm tra & sửa phanh xe', 500000),
                                                      ('Kiểm tra tổng quát', 'Kiểm tra toàn bộ xe', 150000),
                                                      ('Vệ sinh lọc gió', 'Vệ sinh lọc gió động cơ', 100000);

-- ===================================================
-- 3️⃣ NHẬP KHO
-- ===================================================
INSERT INTO tblSparePartReceipt (receiptDate, totalAmount, tblSupplierId, tblWarehouseStaffId) VALUES
                                                                                                   ('2025-09-10', 4500000, 1, 3),
                                                                                                   ('2025-09-15', 2000000, 1, 3);

INSERT INTO tblSparePartReceiptDetail (quantity, totalPrice, tblSparePartReceiptId, tblSparePartId) VALUES
                                                                                                        (10, 500000, 1, 1),
                                                                                                        (5, 2000000, 1, 2),
                                                                                                        (20, 400000, 2, 3);

-- ===================================================
-- 4️⃣ GIAO DỊCH DOANH THU
-- ===================================================

-- Hóa đơn 1
INSERT INTO tblInvoice (id, invoiceDate, totalAmount, tblCustomerId, tblSalesStaffId)
VALUES (1, '2025-10-20', 1230000, 1, 2);
INSERT INTO tblRequest (id, type, description, requestDate, status, tblCustomerId, tblInvoiceId, tblSalesStaffId)
VALUES (1, 'Repair', 'Sửa phanh & thay lọc dầu', '2025-10-19', 'Completed', 1, 1, 2);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 1, 2, 4);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (1, 1, 1), (1, 1, 2);

-- Hóa đơn 2
INSERT INTO tblInvoice VALUES (2, '2025-10-23', 370000, 5, 12);
INSERT INTO tblRequest VALUES (2, 'Maintenance', 'Thay nhớt + bugi', '2025-10-22', 'Completed', 5, 2, 12);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 2, 1, 4);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (2, 2, 3);

-- Hóa đơn 3
INSERT INTO tblInvoice VALUES (3, '2025-10-26', 150000, 7, 2);
INSERT INTO tblRequest VALUES (3, 'Inspection', 'Kiểm tra tổng quát', '2025-10-26', 'Completed', 7, 3, 2);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 3, 3, 4);

-- Hóa đơn 4
INSERT INTO tblInvoice VALUES (4, '2025-10-27', 255000, 8, 13);
INSERT INTO tblRequest VALUES (4, 'Purchase', 'Mua phụ tùng thay thế', '2025-10-27', 'Completed', 8, 4, 13);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (3, 4, 3), (1, 4, 4);

-- Hóa đơn 5
INSERT INTO tblInvoice VALUES (5, '2025-10-28', 600000, 9, 2);
INSERT INTO tblRequest VALUES (5, 'Maintenance', 'Thay nhớt 2 xe', '2025-10-28', 'Completed', 9, 5, 2);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (2, 5, 1, 4);

-- Hóa đơn 6
INSERT INTO tblInvoice VALUES (6, '2025-10-29', 650000, 1, 12);
INSERT INTO tblRequest VALUES (6, 'Purchase', 'Mua má phanh', '2025-10-29', 'Completed', 1, 6, 12);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (1, 6, 2);

-- Hóa đơn 7
INSERT INTO tblInvoice VALUES (7, '2025-10-30', 580000, 8, 13);
INSERT INTO tblRequest VALUES (7, 'Repair', 'Sửa phanh + thay lọc dầu', '2025-10-30', 'Completed', 8, 7, 13);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 7, 2, 4);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (1, 7, 1);

-- Hóa đơn 8
INSERT INTO tblInvoice VALUES (8, '2025-10-31', 185000, 10, 12);
INSERT INTO tblRequest VALUES (8, 'Repair', 'Thay bugi + kiểm tra', '2025-10-31', 'Completed', 10, 8, 12);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 8, 3, 4);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (1, 8, 3);

-- Hóa đơn 9
INSERT INTO tblInvoice VALUES (9, '2025-11-01', 880000, 6, 13);
INSERT INTO tblRequest VALUES (9, 'Maintenance', 'Thay nhớt + sửa phanh', '2025-11-01', 'Completed', 6, 9, 13);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 9, 1, 4), (1, 9, 2, 4);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (1, 9, 1);

-- Hóa đơn 10
INSERT INTO tblInvoice VALUES (10, '2025-11-02', 180000, 5, 2);
INSERT INTO tblRequest VALUES (10, 'Maintenance', 'Vệ sinh lọc gió + thay lọc dầu', '2025-11-02', 'Completed', 5, 10, 2);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 10, 4, 4);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (1, 10, 1);


-- Hóa đơn 11 
INSERT INTO tblInvoice VALUES (11, '2025-10-25', 880000, 7, 12);
INSERT INTO tblRequest VALUES (11, 'Repair', 'Sửa phanh + thay dầu', '2025-10-25', 'Completed', 7, 11, 12);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 11, 1, 4), (1, 11, 2, 4);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (1, 11, 1);

-- Hóa đơn 12
INSERT INTO tblInvoice VALUES (12, '2025-11-03', 150000, 9, 13);
INSERT INTO tblRequest VALUES (12, 'Inspection', 'Kiểm tra xe định kỳ', '2025-11-03', 'Completed', 9, 12, 13);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 12, 3, 4);

-- Hóa đơn 13
INSERT INTO tblInvoice VALUES (13, '2025-11-10', 400000, 1, 2);
INSERT INTO tblRequest VALUES (13, 'Maintenance', 'Thay nhớt + vệ sinh lọc gió', '2025-11-10', 'Completed', 1, 13, 2);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 13, 1, 4), (1, 13, 4, 4);

-- Hóa đơn 14 - Giữa tháng 11
INSERT INTO tblInvoice VALUES (14, '2025-11-15', 1150000, 5, 12);
INSERT INTO tblRequest VALUES (14, 'Repair', 'Sửa phanh + thay má phanh', '2025-11-15', 'Completed', 5, 14, 12);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 14, 2, 4);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (1, 14, 2);

-- Hóa đơn 15
INSERT INTO tblInvoice VALUES (15, '2025-11-25', 300000, 8, 13);
INSERT INTO tblRequest VALUES (15, 'Maintenance', 'Thay nhớt định kỳ', '2025-11-25', 'Completed', 8, 15, 13);
INSERT INTO tblServiceRequest (quantity, tblRequestId, tblServiceId, tblTechnicianId)
VALUES (1, 15, 1, 4);

-- Hóa đơn 16
INSERT INTO tblInvoice VALUES (16, '2025-11-29', 160000, 6, 2);
INSERT INTO tblRequest VALUES (16, 'Purchase', 'Mua phụ tùng lọc dầu', '2025-11-29', 'Completed', 6, 16, 2);
INSERT INTO tblSparePartRequest (quantity, tblRequestId, tblSparePartId)
VALUES (2, 16, 1);

SET FOREIGN_KEY_CHECKS = 1;

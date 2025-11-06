-- ==========================
-- DATABASE: GaraMan
-- ==========================
CREATE DATABASE IF NOT EXISTS GaraMan;
USE GaraMan;

-- ==========================
-- 1. MEMBER (cha)
-- ==========================
CREATE TABLE tblMember (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    birthday DATE,
    address VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phoneNumber VARCHAR(255) UNIQUE,
    role VARCHAR(255),
    gender VARCHAR(20)
);

-- ==========================
-- 2. STAFF (kế thừa MEMBER)
-- ==========================
CREATE TABLE tblStaff (
    tblMemberId INT PRIMARY KEY,
    position VARCHAR(255),
    FOREIGN KEY (tblMemberId) REFERENCES tblMember(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- ==========================
-- 3. TECHNICIAN (kế thừa STAFF)
-- ==========================
CREATE TABLE tblTechnician (
    tblStaffId INT PRIMARY KEY,
    skills VARCHAR(255),
    FOREIGN KEY (tblStaffId) REFERENCES tblStaff(tblMemberId)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- ==========================
-- 4. SUPPLIER
-- ==========================
CREATE TABLE tblSupplier (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    address VARCHAR(255),
    phoneNumber VARCHAR(255),
    email VARCHAR(255)
);

-- ==========================
-- 5. SPARE PART
-- ==========================
CREATE TABLE tblSparePart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    stockQuantity INT DEFAULT 0,
    purchasePrice DECIMAL(18,3),
    sellingPrice DECIMAL(18,3)
);

-- ==========================
-- 6. SPARE PART RECEIPT
-- ==========================
CREATE TABLE tblSparePartReceipt (
    id INT AUTO_INCREMENT PRIMARY KEY,
    receiptDate DATE,
    totalAmount DECIMAL(18,3),
    tblSupplierId INT,
    tblWarehouseStaffId INT,
    FOREIGN KEY (tblSupplierId) REFERENCES tblSupplier(id)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (tblWarehouseStaffId) REFERENCES tblStaff(tblMemberId)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- ==========================
-- 7. SPARE PART RECEIPT DETAIL
-- ==========================
CREATE TABLE tblSparePartReceiptDetail (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT,
    totalPrice DECIMAL(18,3),
    tblSparePartReceiptId INT,
    tblSparePartId INT,
    FOREIGN KEY (tblSparePartReceiptId) REFERENCES tblSparePartReceipt(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tblSparePartId) REFERENCES tblSparePart(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- ==========================
-- 8. INVOICE
-- ==========================
CREATE TABLE tblInvoice (
    id INT AUTO_INCREMENT PRIMARY KEY,
    invoiceDate DATE,
    totalAmount DECIMAL(18,3),
    tblCustomerId INT,
    tblSalesStaffId INT,
    FOREIGN KEY (tblCustomerId) REFERENCES tblMember(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tblSalesStaffId) REFERENCES tblStaff(tblMemberId)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- ==========================
-- 9. REQUEST
-- ==========================
CREATE TABLE tblRequest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255),
    description VARCHAR(255),
    requestDate DATE,
    status VARCHAR(255),
    tblCustomerId INT,
    tblInvoiceId INT,
    tblSalesStaffId INT,
    FOREIGN KEY (tblCustomerId) REFERENCES tblMember(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tblInvoiceId) REFERENCES tblInvoice(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tblSalesStaffId) REFERENCES tblStaff(tblMemberId)
        ON DELETE SET NULL ON UPDATE CASCADE
);

-- ==========================
-- 10. SPARE PART REQUEST
-- ==========================
CREATE TABLE tblSparePartRequest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT,
    tblRequestId INT,
    tblSparePartId INT,
    FOREIGN KEY (tblRequestId) REFERENCES tblRequest(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tblSparePartId) REFERENCES tblSparePart(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- ==========================
-- 11. SERVICE
-- ==========================
CREATE TABLE tblService (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    description VARCHAR(255),
    price DECIMAL(18,3)
);

-- ==========================
-- 12. SERVICE REQUEST
-- ==========================
CREATE TABLE tblServiceRequest (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quantity INT,
    tblRequestId INT,
    tblServiceId INT,
    tblTechnicianId INT,
    FOREIGN KEY (tblRequestId) REFERENCES tblRequest(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tblServiceId) REFERENCES tblService(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (tblTechnicianId) REFERENCES tblTechnician(tblStaffId)
        ON DELETE CASCADE ON UPDATE CASCADE
);

-- ==========================
-- 13. APPOINTMENT
-- ==========================
CREATE TABLE tblAppointment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    appointmentDate DATE,
    appointmentTime TIME,
    status VARCHAR(255),
    tblCustomerId INT,
    FOREIGN KEY (tblCustomerId) REFERENCES tblMember(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

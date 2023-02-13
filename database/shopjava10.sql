-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th2 13, 2023 lúc 06:32 PM
-- Phiên bản máy phục vụ: 10.4.25-MariaDB
-- Phiên bản PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `shopjava10`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_category`
--

CREATE TABLE `tbl_category` (
  `id` int(11) NOT NULL,
  `name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `description` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1,
  `seo` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa danh mục sản phẩm';

--
-- Đang đổ dữ liệu cho bảng `tbl_category`
--

INSERT INTO `tbl_category` (`id`, `name`, `description`, `created_date`, `updated_date`, `created_by`, `updated_by`, `parent_id`, `status`, `seo`) VALUES
(12, 'laptop', 'Laptop', NULL, NULL, NULL, NULL, NULL, 1, 'laptop'),
(13, 'phu-kien', 'Phụ kiện', NULL, NULL, NULL, NULL, NULL, 1, 'phu-kien');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_contact`
--

CREATE TABLE `tbl_contact` (
  `id` int(11) NOT NULL,
  `first_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `request_type` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `message` varchar(1000) COLLATE utf8mb4_bin NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa thông tin liên hệ';

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_products`
--

CREATE TABLE `tbl_products` (
  `id` int(11) NOT NULL,
  `title` varchar(1000) CHARACTER SET utf8 NOT NULL,
  `price` decimal(13,2) DEFAULT NULL,
  `short_description` varchar(3000) CHARACTER SET utf8 NOT NULL,
  `detail_description` varchar(3000) COLLATE utf8mb4_bin NOT NULL,
  `avatar` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1,
  `seo` varchar(1000) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa danh mục sản phẩm';

--
-- Đang đổ dữ liệu cho bảng `tbl_products`
--

INSERT INTO `tbl_products` (`id`, `title`, `price`, `short_description`, `detail_description`, `avatar`, `category_id`, `created_date`, `updated_date`, `created_by`, `updated_by`, `status`, `seo`) VALUES
(37, 'Laptop', '15000000.00', 'demo1', '', NULL, 12, NULL, NULL, NULL, NULL, 1, 'laptop-1676303087378'),
(38, 'Laptop1', '15000000.00', 'demo1', '', NULL, 12, NULL, NULL, NULL, NULL, 1, 'laptop1-1676303239038'),
(39, 'Laptop2', '15000000.00', 'demo2', '', NULL, 12, NULL, NULL, NULL, NULL, 1, 'laptop2-1676303269533'),
(40, 'Laptop3', '15000000.00', 'demo3', '', NULL, 12, NULL, NULL, NULL, NULL, 1, 'laptop3-1676303297738'),
(41, 'Phụ Kiện 1', '29000.00', 'phu kien', '', NULL, 13, NULL, NULL, NULL, NULL, 1, 'phu-kien-1-1676303341565'),
(42, 'Phụ Kiện 2', '29000.00', 'phu kien', '', NULL, 13, NULL, NULL, NULL, NULL, 1, 'phu-kien-2-1676303383162'),
(43, 'Phụ Kiện 3', '29000.00', 'phu kien', '', NULL, 13, NULL, NULL, NULL, NULL, 1, 'phu-kien-3-1676303418685'),
(44, 'Phụ Kiện 4', '2000000.00', 'phu kien', '', NULL, 13, NULL, NULL, NULL, NULL, 1, 'phu-kien-4-1676303451351');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_products_images`
--

CREATE TABLE `tbl_products_images` (
  `id` int(11) NOT NULL,
  `title` varchar(500) CHARACTER SET utf8 NOT NULL,
  `path` varchar(200) COLLATE utf8mb4_bin NOT NULL,
  `product_id` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu danh sách ảnh sản phẩm';

--
-- Đang đổ dữ liệu cho bảng `tbl_products_images`
--

INSERT INTO `tbl_products_images` (`id`, `title`, `path`, `product_id`, `created_date`, `updated_date`, `created_by`, `updated_by`, `status`) VALUES
(14, 'asus.jpg', 'asus.jpg', 37, NULL, NULL, NULL, NULL, 0),
(15, 'chitiet-1.jpg', 'chitiet-1.jpg', 37, NULL, NULL, NULL, NULL, 0),
(16, 'chitiet-2 - Copy.jpg', 'chitiet-2 - Copy.jpg', 37, NULL, NULL, NULL, NULL, 0),
(17, 'chitiet-3 - Copy.jpg', 'chitiet-3 - Copy.jpg', 37, NULL, NULL, NULL, NULL, 0),
(18, 'chitiet-4.jpg', 'chitiet-4.jpg', 37, NULL, NULL, NULL, NULL, 0),
(19, 'asus.jpg', 'asus.jpg', 38, NULL, NULL, NULL, NULL, 0),
(20, 'chitiet-1.jpg', 'chitiet-1.jpg', 38, NULL, NULL, NULL, NULL, 0),
(21, 'chitiet-2 - Copy.jpg', 'chitiet-2 - Copy.jpg', 38, NULL, NULL, NULL, NULL, 0),
(22, 'chitiet-3 - Copy.jpg', 'chitiet-3 - Copy.jpg', 38, NULL, NULL, NULL, NULL, 0),
(23, 'chitiet-4.jpg', 'chitiet-4.jpg', 38, NULL, NULL, NULL, NULL, 0),
(24, 'chitiet-5.jpg', 'chitiet-5.jpg', 38, NULL, NULL, NULL, NULL, 0),
(25, 'asus.jpg', 'asus.jpg', 39, NULL, NULL, NULL, NULL, 0),
(26, 'chitiet-1.jpg', 'chitiet-1.jpg', 39, NULL, NULL, NULL, NULL, 0),
(27, 'chitiet-2 - Copy.jpg', 'chitiet-2 - Copy.jpg', 39, NULL, NULL, NULL, NULL, 0),
(28, 'chitiet-3 - Copy.jpg', 'chitiet-3 - Copy.jpg', 39, NULL, NULL, NULL, NULL, 0),
(29, 'chitiet-4.jpg', 'chitiet-4.jpg', 39, NULL, NULL, NULL, NULL, 0),
(30, 'chitiet-5.jpg', 'chitiet-5.jpg', 39, NULL, NULL, NULL, NULL, 0),
(31, 'asus.jpg', 'asus.jpg', 40, NULL, NULL, NULL, NULL, 0),
(32, 'chitiet-1.jpg', 'chitiet-1.jpg', 40, NULL, NULL, NULL, NULL, 0),
(33, 'chitiet-2 - Copy.jpg', 'chitiet-2 - Copy.jpg', 40, NULL, NULL, NULL, NULL, 0),
(34, 'chitiet-3 - Copy.jpg', 'chitiet-3 - Copy.jpg', 40, NULL, NULL, NULL, NULL, 0),
(35, 'chitiet-4.jpg', 'chitiet-4.jpg', 40, NULL, NULL, NULL, NULL, 0),
(36, 'sanpham-6.jpg', 'sanpham-6.jpg', 41, NULL, NULL, NULL, NULL, 0),
(37, 'sanpham-2.jpg', 'sanpham-2.jpg', 42, NULL, NULL, NULL, NULL, 0),
(38, 'sanpham-1.jpg', 'sanpham-1.jpg', 43, NULL, NULL, NULL, NULL, 0),
(39, 'sanpham-1.jpg', 'sanpham-1.jpg', 44, NULL, NULL, NULL, NULL, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_roles`
--

CREATE TABLE `tbl_roles` (
  `id` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `description` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `created_date` datetime NOT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa thông tin roles';

--
-- Đang đổ dữ liệu cho bảng `tbl_roles`
--

INSERT INTO `tbl_roles` (`id`, `name`, `description`, `created_date`, `updated_date`, `created_by`, `updated_by`, `status`) VALUES
(1, 'admin', '', '2023-02-13 15:57:19', NULL, NULL, NULL, 1),
(2, 'user', '', '2023-02-13 15:57:48', NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_saleorder`
--

CREATE TABLE `tbl_saleorder` (
  `id` int(11) NOT NULL,
  `code` varchar(45) CHARACTER SET utf8 NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `total` decimal(13,2) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1,
  `customer_name` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `customer_address` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `customer_email` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `customer_phone` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `seo` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa phiếu mua hàng';

--
-- Đang đổ dữ liệu cho bảng `tbl_saleorder`
--

INSERT INTO `tbl_saleorder` (`id`, `code`, `user_id`, `total`, `created_date`, `updated_date`, `created_by`, `updated_by`, `status`, `customer_name`, `customer_address`, `customer_email`, `customer_phone`, `seo`) VALUES
(9, 'ORDER-1676307211023', NULL, '30000000.00', '2023-02-13 23:53:31', NULL, NULL, NULL, 1, 'admin', 'hcm', 'a@email.com', '012345', 'ORDER-1676307211023');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_saleorder_products`
--

CREATE TABLE `tbl_saleorder_products` (
  `id` int(11) NOT NULL,
  `saleorder_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quality` int(11) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa phiếu mua hàng';

--
-- Đang đổ dữ liệu cho bảng `tbl_saleorder_products`
--

INSERT INTO `tbl_saleorder_products` (`id`, `saleorder_id`, `product_id`, `quality`, `created_date`, `updated_date`, `created_by`, `updated_by`, `status`) VALUES
(12, 9, 37, 1, NULL, NULL, NULL, NULL, 1),
(13, 9, 38, 1, NULL, NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_users`
--

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL,
  `username` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `first_name` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `name` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `phone` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_bin NOT NULL,
  `email` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `address` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `avatar` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='Bảng dữ liệu chứa thông tin người dùng';

--
-- Đang đổ dữ liệu cho bảng `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `username`, `first_name`, `name`, `phone`, `password`, `email`, `gender`, `address`, `avatar`, `created_date`, `updated_date`, `created_by`, `updated_by`, `status`) VALUES
(5, 'user1', 'tan', 'male', '0376892394', '$2a$08$4exwbDSyUsJM2fcadKhCPOnOw/QIaBdjhjcCrN7vAB8Z6JOtDBFW6', 'duy@gmail.com', 1, 'thành phố Hồ Chí Minh Việt Nam', NULL, NULL, '2023-02-13 22:28:02', NULL, NULL, 1),
(6, 'admin', 'admin', 'admin', '012345', '$2a$08$4exwbDSyUsJM2fcadKhCPOnOw/QIaBdjhjcCrN7vAB8Z6JOtDBFW6', 'a@email.com', 0, 'hcm', 'asus.jpg', '2023-02-14 00:17:59', NULL, NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tbl_users_roles`
--

CREATE TABLE `tbl_users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='bảng trung gian thể hiện quan hệ n-n của users và roles';

--
-- Đang đổ dữ liệu cho bảng `tbl_users_roles`
--

INSERT INTO `tbl_users_roles` (`user_id`, `role_id`) VALUES
(5, 2),
(6, 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `tbl_category`
--
ALTER TABLE `tbl_category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_internal_idx` (`parent_id`);

--
-- Chỉ mục cho bảng `tbl_contact`
--
ALTER TABLE `tbl_contact`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_products`
--
ALTER TABLE `tbl_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_category_products_idx` (`category_id`);

--
-- Chỉ mục cho bảng `tbl_products_images`
--
ALTER TABLE `tbl_products_images`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_product_images_idx` (`product_id`);

--
-- Chỉ mục cho bảng `tbl_roles`
--
ALTER TABLE `tbl_roles`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_saleorder`
--
ALTER TABLE `tbl_saleorder`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_saleorder_products`
--
ALTER TABLE `tbl_saleorder_products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_saleorder_product_idx` (`saleorder_id`);

--
-- Chỉ mục cho bảng `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `tbl_users_roles`
--
ALTER TABLE `tbl_users_roles`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `fk_roles_idx` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `tbl_category`
--
ALTER TABLE `tbl_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `tbl_contact`
--
ALTER TABLE `tbl_contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `tbl_products`
--
ALTER TABLE `tbl_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT cho bảng `tbl_products_images`
--
ALTER TABLE `tbl_products_images`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT cho bảng `tbl_roles`
--
ALTER TABLE `tbl_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tbl_saleorder`
--
ALTER TABLE `tbl_saleorder`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `tbl_saleorder_products`
--
ALTER TABLE `tbl_saleorder_products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT cho bảng `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `tbl_category`
--
ALTER TABLE `tbl_category`
  ADD CONSTRAINT `fk_internal` FOREIGN KEY (`parent_id`) REFERENCES `tbl_category` (`id`);

--
-- Các ràng buộc cho bảng `tbl_products`
--
ALTER TABLE `tbl_products`
  ADD CONSTRAINT `fk_category_products` FOREIGN KEY (`category_id`) REFERENCES `tbl_category` (`id`);

--
-- Các ràng buộc cho bảng `tbl_products_images`
--
ALTER TABLE `tbl_products_images`
  ADD CONSTRAINT `fk_product_images` FOREIGN KEY (`product_id`) REFERENCES `tbl_products` (`id`);

--
-- Các ràng buộc cho bảng `tbl_saleorder_products`
--
ALTER TABLE `tbl_saleorder_products`
  ADD CONSTRAINT `fk_saleorder_product` FOREIGN KEY (`saleorder_id`) REFERENCES `tbl_saleorder` (`id`);

--
-- Các ràng buộc cho bảng `tbl_users_roles`
--
ALTER TABLE `tbl_users_roles`
  ADD CONSTRAINT `fk_roles` FOREIGN KEY (`role_id`) REFERENCES `tbl_roles` (`id`),
  ADD CONSTRAINT `fk_users` FOREIGN KEY (`user_id`) REFERENCES `tbl_users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

package com.devpro.services;

import com.devpro.GeneratePassword;
import com.devpro.entities.Role;
import com.devpro.entities.User;
import com.devpro.repositories.RoleRepo;
import com.devpro.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.File;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final EntityManager entityManager;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public User findUserById(final int id) {

        String sql = "select * from tbl_users where id = '" + id + "'";
        Query query = entityManager.createNativeQuery(sql, User.class);
        return (User) query.getSingleResult();
    }

    public Role findRoleById(final int id) {

        String sql = "select * from tbl_roles where id = '" + id + "'";
        Query query = entityManager.createNativeQuery(sql, Role.class);
        return (Role) query.getSingleResult();
    }

    private boolean isEmptyUploadFile(MultipartFile[] images) {
        if (images == null || images.length == 0)
            return true;
        return images.length == 1 && Objects.requireNonNull(images[0].getOriginalFilename()).isEmpty();
    }

    public User loadUserByUsername(String userName) {
        try {
            String jpql = "From User u Where u.username='" + userName + "'";
            Query query = entityManager.createQuery(jpql, User.class);
            return (User) query.getResultList().get(0);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveUser(MultipartFile[] images, User user, HttpServletRequest request) throws Exception {
        try {
            if (user.getId() != null) { // chỉnh sửa
                // lấy dữ liệu cũ của sản phẩm
                User userInDb = userRepo.findById(user.getId()).get();
                // lấy danh sách ảnh của user cũ
                String avatar = userInDb.getAvatar();
                if (!isEmptyUploadFile(images)) { // nếu admin sửa ảnh sản phẩm
                    // xoá ảnh cũ đi
                    String rootDirectory = request.getSession().getServletContext().getRealPath("/");
                    new File(rootDirectory + "/upload_avt/" + user.getAvatar()).delete();
                } else { // ảnh phải giữ nguyên
                    user.setAvatar(avatar);
                }
            }
            if (!isEmptyUploadFile(images)) { // nếu admin upload ảnh
                for (MultipartFile image : images) {
                    // Lưu file vào host.
                    String rootDirectory = request.getSession().getServletContext().getRealPath("/");
                    image.transferTo(new File(rootDirectory +
                            "/upload_avt/" + image.getOriginalFilename()));
                    user.setAvatar(image.getOriginalFilename());
                }
            }
            if (user.getCreatedDate() != null) {
                user.setUpdatedDate(java.time.LocalDateTime.now());
            } else {
                user.setCreatedDate(java.time.LocalDateTime.now());
            }

            userRepo.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveGuestUser(User user) throws Exception {
        try {
            user.setPassword(GeneratePassword.GenerPass(user.getPassword()));
            user.getRoles().add(findRoleById(2));
            user.setUpdatedDate(java.time.LocalDateTime.now());
            user.setStatus(true);
            userRepo.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

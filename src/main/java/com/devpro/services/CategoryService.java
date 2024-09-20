package com.devpro.services;

import com.devpro.common.Utilities;
import com.devpro.entities.Category;
import com.devpro.repositories.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {
    private final CategoryRepo categoryRepo;
    private final EntityManager entityManager;

    public Category findCategoryBySeo(final String seo) {

        String sql = "select * from tbl_category where seo = '" + seo + "'";
        Query query = entityManager.createNativeQuery(sql, Category.class);
        return (Category) query.getSingleResult();
    }

    @Transactional(rollbackOn = Exception.class)
    public void saveCategory(Category category) {
        try {
            category.setSeo(Utilities.createSeoLinkCategory(category.getName()));

            categoryRepo.save(category);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}

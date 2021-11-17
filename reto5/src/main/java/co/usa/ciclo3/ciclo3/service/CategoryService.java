package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    //Metodo GET
    public List<Category> getAll(){
        return categoryRepository.getAll();
    }
    //Metodo GET por ID
    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }
    //Metodo POST
    public Category save(Category c){
        if (c.getId()==null){
            return categoryRepository.save(c);
        }else{
            Optional<Category> caaux=categoryRepository.getCategory(c.getId());
            if (caaux.isEmpty()){
                return categoryRepository.save(c);
            }else {
                return c;
            }
        }
    }
    //Metodo PUT
    public Category update(Category category){
        if (category.getId()!=null){
            Optional<Category>g=categoryRepository.getCategory(category.getId());
            if (!g.isEmpty()){
                if (category.getName()!=null){
                    g.get().setName(category.getName());
                }
                if (category.getDescription()!=null) {
                    g.get().setDescription(category.getDescription());
                }
                return categoryRepository.save(g.get());
            }
        }
        return category;
    }

    //Metodo DELETE
    public boolean deleteCategory(int id){
        Optional<Category> c=getCategory(id);
        if (!c.isEmpty()){
            categoryRepository.delete(c.get());
            return true;
        }
        return false;
    }
}

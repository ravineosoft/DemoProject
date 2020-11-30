package com.staqo.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.staqo.poc.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	
    public List<User> findTop5ByOrderByIdDesc();
    
    
    @Query(value="select to_char(created_date,'Mon') as first_name, count(*) as id, CURRENT_DATE as created_date, '' as email, '' as last_name, '' as phone_number, '' as photo, false as active from guest_user where active=true group by 1 order by created_date",nativeQuery=true)
	public List<User> getCountByMonth() ;
    
    
    public User findByIdAndActive(Long id, Boolean active);
    

}

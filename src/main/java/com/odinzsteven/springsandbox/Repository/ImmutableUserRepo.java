package com.odinzsteven.springsandbox.Repository;

import com.odinzsteven.springsandbox.Entity.ImmutableUser;
import com.odinzsteven.springsandbox.Entity.MutableUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImmutableUserRepo extends JpaRepository<ImmutableUser, Long> {

}

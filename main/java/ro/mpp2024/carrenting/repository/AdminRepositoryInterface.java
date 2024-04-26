package ro.mpp2024.carrenting.repository;

import ro.mpp2024.carrenting.domain.Admin;

public interface AdminRepositoryInterface extends Repository<Long, Admin>{
    public Long findAdminUniqueCode(String uniqueCode);

}

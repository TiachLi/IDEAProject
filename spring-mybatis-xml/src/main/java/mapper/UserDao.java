package mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    public int findTotalCounts();

}

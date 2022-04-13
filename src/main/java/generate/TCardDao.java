package generate;

import generator.TCard;
import org.springframework.stereotype.Repository;

@Repository
public interface TCardDao {

    int deleteByPrimaryKey(String cardId);

    int insert(TCard record);

    int insertSelective(TCard record);

    TCard selectByPrimaryKey(String cardId);

    int updateByPrimaryKeySelective(TCard record);

    int updateByPrimaryKey(TCard record);
}
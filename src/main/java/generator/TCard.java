package generator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * t_card
 * @author 
 */
@ApiModel(value="generator.TCard")
@Data
public class TCard implements Serializable {
    private String cardId;

    private String userId;

    private Long cardNum;

    private String cardName;

    private Byte del;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}
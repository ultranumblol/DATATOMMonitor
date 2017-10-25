package datatom.com.datatommonitor.Entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wgz on 2017/8/2.
 */
@Entity
public class ProductBean {

    @Id(autoincrement = true)
    private Long id;

    private String name;
    private String ip;
    private String node;
    private String pwd;

    @Generated(hash = 2088206770)
    public ProductBean(Long id, String name, String ip, String node, String pwd) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.node = node;
        this.pwd = pwd;
    }

    @Generated(hash = 669380001)
    public ProductBean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNode() {
        return this.node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getPwd() {
        return this.pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

}

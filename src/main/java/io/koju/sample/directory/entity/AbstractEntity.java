package io.koju.sample.directory.entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * Created by Kapil Koju on 3/19/14.
 */
@MappedSuperclass
public abstract class AbstractEntity extends AbstractPersistable<Long> {

    protected Date created;

    protected Date lastModified;


    @Version
    private Long version;

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

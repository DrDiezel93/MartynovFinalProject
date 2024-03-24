package dao.mybatis;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import dao.DocsDao;
import entity.Docs;

public class DocsMapper extends SqlSessionDaoSupport implements DocsDao {

    public void create(Docs obj) {
        getSqlSession().insert("Docs.create", obj);
    }

    public Docs read(Long id) {
        return getSqlSession().selectOne("Docs.read", id); 
    }

    public void update(Docs obj) {
        getSqlSession().update("Docs.update", obj);
    }

    public void delete(Long id) {
        getSqlSession().delete("Docs.delete", id);
    }

    @Override
    public List<Docs> findAll() {
        return getSqlSession().selectList("Docs.findAll");
    }

    @Override
    public List<Docs> findByUserId(Long id) {
        return getSqlSession().selectList("Docs.findByUserId",id);
    }

    @Override
    public void deleteByUserId(Long id) {
        getSqlSession().delete("Docs.deleteByUserId", id);
    }

    @Override
    public List<Docs> selectTop20() {
        return getSqlSession().selectList("Docs.selectTop20");
    }
    
    @Override
    public List<Docs> findDocsByDate(Docs docs) {
        return getSqlSession().selectList("Docs.findDocsByDate", docs);
    }
    
    public List<Docs> findDocsByName(Docs docs) {
        return getSqlSession().selectList("Docs.findDocsByName", docs);
    }

	@Override
	public List<Docs> findByTwo(Docs docs) {
        return getSqlSession().selectList("Docs.findByTwo", docs);
	}

	@Override
	public List<Docs> findDocsSpec() {
		return getSqlSession().selectList("Docs.findDocsSpec");
	}

	@Override
	public List<Docs> findDocsAsp() {
		return getSqlSession().selectList("Docs.findDocsAsp");
	}

	@Override
	public List<Docs> findDocsMag() {
		return getSqlSession().selectList("Docs.findDocsMag");
	}

	@Override
	public List<Docs> findDocsBak() {
		return getSqlSession().selectList("Docs.findDocsBak");
	}

}

package pw.marques.squash.services;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pw.marques.squash.dao.DaoFactory;
import pw.marques.squash.domain.Group;
import pw.marques.squash.domain.User;
import pw.marques.squash.domain.Court;


@Component("courtService")

public class CourtService {
	@Autowired
	private DaoFactory daoFactory;
	@Transactional
	public Court findByCourt(String court) {
		Court result = daoFactory.getDao(Court.class).findOneByCriteria(Restrictions.eq("courtName",  court));
		return result;
	}
	@Transactional
	public Court findByID(long court){
		Court result = daoFactory.getDao(Court.class).findOneByCriteria(Restrictions.eq("courtID",  court));
		return result;
	}
	
	@Transactional
	public void installNewCourt(Court court) {
		daoFactory.getDao(Court.class).save(court);
	}

}

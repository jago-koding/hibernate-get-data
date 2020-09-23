package id.jagokoding;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

	public static void main(String[] args) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.getTransaction();
			transaction.begin();

			// dengan method get()
			Customer customer1 = session.get(Customer.class, 1L);
			if (customer1 != null) {
				System.out.println(customer1.getName());
			}

			// dengan method load()
			Customer customer2 = session.load(Customer.class, 2L);
			System.out.println(customer2.getName());

			// dengan method byId()
			Customer customer3 = session.byId(Customer.class).getReference(3L);
			System.out.println(customer3.getName());

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		HibernateUtil.shutdown();
	}
}

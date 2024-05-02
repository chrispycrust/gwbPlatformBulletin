package com.fdmgroup.model;

/**
 * Dummy data for app
 * 
 * @author Christine Nguyen
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Runner {

	public static void main(String[] args) {
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("gwbPlatform");
//		EntityManager em = emf.createEntityManager(); 
//		
//		// ------------------------------------
//		//	create member types
//		// ------------------------------------
//		
//		MemberType internal = new MemberType("internal");
//		MemberType normal = new MemberType("normal");
//		MemberType mentor = new MemberType("mentor");
//		MemberType mentee = new MemberType("mentee");
//		MemberType host = new MemberType("host");
//		
//		em.getTransaction().begin();
//		{
//			em.persist(internal);
//			em.persist(normal);
//			em.persist(mentor);
//			em.persist(mentee);
//			em.persist(host);
//		}
//		em.getTransaction().commit();
//		
//		// ------------------------------------
//		//	create dummy members
//		// ------------------------------------
//		
////		List<Member> members = new ArrayList<>();
////		members.add(new Member("Eilish", "Jackson", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Vanessa", "A", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Queen", "Charlotte", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Lady", "Danbury", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Lady", "Whistledown", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Violet", "Bridgerton", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Daphne", "Bridgerton", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Eloise", "Bridgerton", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Mary", "Sharma", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Kate", "Sharma", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Edwina", "Sharma", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Alice", "Mondrich", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Portia", "Featherington", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Penelope", "Featherington", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Siena", "Rosso", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Marina", "Thompson", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Genevieve", "Delacroix", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Cressida", "Cowper", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Rose", "Nolan", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Mrs", "Varley", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
////		members.add(new Member("Mrs", "Wilson", new HashSet<>(), new ArrayList<>(), new ArrayList<>()));
//		
//		// store members in Members database
//		
//		em.getTransaction().begin();
//		
//		try {
//		    
//		    for (Member member : members) {
//		        em.persist(member);
//		        
//		    }
//		    em.getTransaction().commit();
//		    
//		} catch (Exception e) {
//		    
//		    em.getTransaction().rollback();
//		    e.printStackTrace(); 
//		}
//		
//		
//		// ------------------------------------
//		// create dummy membership types
//		// ------------------------------------
//		
//		List<Membership> memberships = new ArrayList<>();
//		memberships.add(new Membership(members.get(0), internal));
//		memberships.add(new Membership(members.get(1), internal));
//		memberships.add(new Membership(members.get(2), host));
//		memberships.add(new Membership(members.get(3), mentor));
//		memberships.add(new Membership(members.get(3), host));
//		memberships.add(new Membership(members.get(4), normal));
//		memberships.add(new Membership(members.get(5), host));
//		memberships.add(new Membership(members.get(6), host));
//		memberships.add(new Membership(members.get(6), mentor));
//		memberships.add(new Membership(members.get(7), normal));
//		memberships.add(new Membership(members.get(8), normal));
//		memberships.add(new Membership(members.get(9), mentor));
//		memberships.add(new Membership(members.get(9), mentee));
//		memberships.add(new Membership(members.get(10), mentee));
//		memberships.add(new Membership(members.get(11), normal));
//		memberships.add(new Membership(members.get(12), host));
//		memberships.add(new Membership(members.get(13), normal));
//		memberships.add(new Membership(members.get(14), normal));
//		memberships.add(new Membership(members.get(15), normal));
//		memberships.add(new Membership(members.get(16), normal));
//		memberships.add(new Membership(members.get(17), normal));
//		memberships.add(new Membership(members.get(18), mentee));
//		memberships.add(new Membership(members.get(19), normal));
//		memberships.add(new Membership(members.get(20), mentor));
//		
//		em.getTransaction().begin();
//		
//		try {
//		    
//		    for (Membership membership : memberships) {
//		        em.persist(membership);
//		        
//		    }
//		    em.getTransaction().commit();
//		    
//		} catch (Exception e) {
//		    
//		    em.getTransaction().rollback();
//		    e.printStackTrace(); 
//		}
//
//		// ------------------------------------
//		// create dummy connection types
//		// ------------------------------------
//		
//		ConnectionType newConnection = new ConnectionType("new");
//		ConnectionType developing = new ConnectionType("developing");
//		ConnectionType mentorship = new ConnectionType("mentorship");
//		ConnectionType personal = new ConnectionType("personal");
//		ConnectionType professional = new ConnectionType("professional");
//		
//		em.getTransaction().begin();
//		{
//			em.persist(newConnection);
//			em.persist(developing);
//			em.persist(mentorship);
//			em.persist(personal);
//			em.persist(professional);
//		}
//		em.getTransaction().commit();
//		
//		// ------------------------------------
//		// create dummy connections between members
//		// ------------------------------------
//		
//		List<Connection> connections = new ArrayList<>();
//		connections.add(new Connection(members.get(0), members.get(1), professional));
//		connections.add(new Connection(members.get(2), members.get(3), personal));
////		connections.add(new Connection(members.get(2), members.get(4), newConnection));
////		connections.add(new Connection(members.get(3), members.get(5), personal));
////		connections.add(new Connection(members.get(3), members.get(9), mentorship));
////		connections.add(new Connection(members.get(5), members.get(2), professional));
////		connections.add(new Connection(members.get(5), members.get(6), personal));
////		connections.add(new Connection(members.get(5), members.get(7), personal));
////		connections.add(new Connection(members.get(5), members.get(8), personal));
////		connections.add(new Connection(members.get(5), members.get(9), personal));
////		connections.add(new Connection(members.get(5), members.get(10), personal));
////		connections.add(new Connection(members.get(5), members.get(12), professional));
////		connections.add(new Connection(members.get(5), members.get(16), professional));
////		connections.add(new Connection(members.get(5), members.get(20), professional));
////		connections.add(new Connection(members.get(6), members.get(7), personal));
////		connections.add(new Connection(members.get(6), members.get(2), professional));
////		connections.add(new Connection(members.get(6), members.get(8), personal));
////		connections.add(new Connection(members.get(6), members.get(9), personal));
////		connections.add(new Connection(members.get(6), members.get(10), personal));
////		connections.add(new Connection(members.get(6), members.get(16), professional));
////		connections.add(new Connection(members.get(6), members.get(15), professional));
////		connections.add(new Connection(members.get(6), members.get(17), professional));
////		connections.add(new Connection(members.get(6), members.get(18), professional));
////		connections.add(new Connection(members.get(7), members.get(2), professional));
////		connections.add(new Connection(members.get(7), members.get(9), newConnection));
////		connections.add(new Connection(members.get(7), members.get(13), personal));
////		connections.add(new Connection(members.get(8), members.get(3), professional));
////		connections.add(new Connection(members.get(8), members.get(5), personal));
////		connections.add(new Connection(members.get(8), members.get(6), personal));
////		connections.add(new Connection(members.get(8), members.get(7), personal));
////		connections.add(new Connection(members.get(8), members.get(9), personal));
////		connections.add(new Connection(members.get(8), members.get(10), personal));
////		connections.add(new Connection(members.get(9), members.get(5), personal));
////		connections.add(new Connection(members.get(9), members.get(6), personal));
////		connections.add(new Connection(members.get(9), members.get(7), personal));
////		connections.add(new Connection(members.get(9), members.get(10), personal));
////		connections.add(new Connection(members.get(9), members.get(10), mentorship));
////		connections.add(new Connection(members.get(10), members.get(2), mentorship));
////		connections.add(new Connection(members.get(10), members.get(5), personal));
////		connections.add(new Connection(members.get(10), members.get(6), personal));
////		connections.add(new Connection(members.get(10), members.get(7), personal));
////		connections.add(new Connection(members.get(12), members.get(13), personal));
////		connections.add(new Connection(members.get(12), members.get(19), professional));
////		connections.add(new Connection(members.get(13), members.get(3), professional));
////		connections.add(new Connection(members.get(13), members.get(5), personal));
////		connections.add(new Connection(members.get(13), members.get(6), personal));
////		connections.add(new Connection(members.get(13), members.get(7), personal));
////		connections.add(new Connection(members.get(13), members.get(15), personal));
////		connections.add(new Connection(members.get(13), members.get(19), professional));
////		connections.add(new Connection(members.get(13), members.get(16), newConnection));
////		connections.add(new Connection(members.get(14), members.get(16), personal));
////		connections.add(new Connection(members.get(18), members.get(20), mentorship));
////		connections.add(new Connection(members.get(19), members.get(20), professional));
//
//		// store memberConnection in memberConnection database
//		
//		em.getTransaction().begin();
//		
//		try {
//		    
//		    for (Connection connection : connections) {
//		        em.persist(connection);
//		        
//		    }
//		    em.getTransaction().commit();
//		    
//		} catch (Exception e) {
//		    
//		    em.getTransaction().rollback();
//		    e.printStackTrace(); 
//		}
//		
//		// check if 'cascade' operation is working
//		Member foundMember = em.find(Member.class, 1);
//		System.out.println(foundMember);
//		
//		em.getTransaction().begin();
//			em.remove(foundMember);
//		em.getTransaction().commit();
		

	}

}

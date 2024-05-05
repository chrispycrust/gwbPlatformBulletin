/**
 * Dummy data for app
 * 
 * @author Christine Nguyen
 * @version 1.0
 */
package com.fdmgroup.gwbPlatformBulletin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;

import com.fdmgroup.gwbPlatformBulletin.dal.BulletinPostRepository;
import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;
import com.fdmgroup.gwbPlatformBulletin.model.BulletinPost;
import com.fdmgroup.gwbPlatformBulletin.model.Member;
import com.fdmgroup.gwbPlatformBulletin.service.BulletinPostService;
import com.fdmgroup.gwbPlatformBulletin.service.MemberService;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class Runner implements ApplicationRunner {
	
	ApplicationContext context;
	MemberRepository memberRepo;
	MemberService memberService;
	BulletinPostRepository bulletinRepo;
	BulletinPostService bulletinService;
	
	@Autowired
	public Runner(ApplicationContext context, MemberRepository memberRepo, 
			MemberService memberService, BulletinPostRepository bulletinRepo, 
			BulletinPostService bulletinService) {
		this.context = context;
		this.memberService = memberService;
		this.bulletinRepo = bulletinRepo;
		this.bulletinService = bulletinService;
		
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		
		// ------------------------------------
		//	create dummy members
		// ------------------------------------
		
		List<Member> members = new ArrayList<>();
		members.add(new Member("Miss", "Eilish", "Jackson", new ArrayList<>()));
		members.add(new Member("Ms", "Vanessa", "A", new ArrayList<>()));
		members.add(new Member("Her Majesty Queen", "Charlotte", "of Mecklenburg-Strelitz", new ArrayList<>()));
		members.add(new Member("Lady", "Agatha", "Danbury", new ArrayList<>()));
		members.add(new Member("Lady", "", "Whistledown", new ArrayList<>()));
		members.add(new Member("Lady", "Violet", "Bridgerton", new ArrayList<>()));
		members.add(new Member("Duchess", "Daphne", "Basset", new ArrayList<>()));
		members.add(new Member("The Honourable", "Eloise", "Bridgerton", new ArrayList<>()));
		members.add(new Member("Miss", "Mary", "Sharma", new ArrayList<>()));
		members.add(new Member("Viscountess", "Kate", "Bridgerton", new ArrayList<>()));
		members.add(new Member("Miss", "Edwina", "Sharma", new ArrayList<>()));
		members.add(new Member("Mrs", "Alice", "Mondrich", new ArrayList<>()));
		members.add(new Member("Lady", "Portia", "Featherington", new ArrayList<>()));
		members.add(new Member("Miss", "Penelope", "Featherington", new ArrayList<>()));
		members.add(new Member("Miss", "Siena", "Rosso", new ArrayList<>()));
		members.add(new Member("Lady", "Marina", "Crane", new ArrayList<>()));
		members.add(new Member("Madame", "Genevieve", "Delacroix", new ArrayList<>()));
		members.add(new Member("Miss", "Cressida", "Cowper", new ArrayList<>()));
		members.add(new Member("Miss", "Rose", "Nolan", new ArrayList<>()));
		members.add(new Member("Mrs", "", "Varley", new ArrayList<>()));
		members.add(new Member("Mrs", "", "Wilson", new ArrayList<>()));
		
		memberService.saveAll(members);
		
		// ------------------------------------
		// create dummy posts
		// ------------------------------------
		
		List<BulletinPost> bulletinBoard = new ArrayList<>();
		
		bulletinBoard.add(new BulletinPost(members.get(0), 
				
				"Welcome!", 
				
				"Attention all Lady Bosses! Welcome to Maidens of Influence, a community where we forge strong connections with kindred spirits.\n"
				+ "\n"
				+ "Our aim is to establish a sisterhood of women who aspire to lead lives of significance, regardless of its manifestation (or whether you are acquainted with such manifestation!), and to foster serendipitous collaboration and mutual learning. We are a diverse assembly of individuals encompassing various lifestyles, vocations, cultures, and ages, advocating for nonconformity and genuineness. This realm is a haven for us to frolic, experiment with ideas, and uphold one another sans reproach. We revel in merriment and enlightenment whilst cultivating meaningful connections in both life and commerce.\n"
				+ "\n"
				+ "Art you on board? I think you are ;)\n"
				+ "\n"
				+ "Throughout the land, we convene frequent gatherings led by members themselves, encompassing workshops for professional and personal growth, convivial assemblies, lectures by esteemed speakers, social diversions, literary circles, and informal coffee rendezvous for networking. All members are welcome and encouraged to organise their own gatherings within the fellowship! Should you possess an idea to explore or wish to share thy wisdom with the fellowship, do not hesitate to approach one of our conveners. Our channels of communication are ever open!"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(2), 
				
				"Attention, London Elite: Tea Gathering at Buckingham Palace!", 
				
				"You are hereby summoned to partake in a tea gathering of utmost refinement at the esteemed Buckingham Palace Gardens. Indulge in the finest teas and delectable treats amidst the regal splendor of the palace grounds.\n"
				+ "\n"
				+ "Date: Saturday, May 15th\n"
				+ "Time: 3:00 PM - 6:00 PM\n"
				+ "Location: Buckingham Palace Gardens, London\n"
				+ "\n"
				+ "Attire: Dress to impress in your most fashionable attire. Those lacking in sartorial elegance need not apply.\n"
				+ "\n"
				+ "RSVP promptly by May 10th to secure your place among the elite. Be warned: tardiness will not be tolerated.\n"
				+ "\n"
				+ "Yours imperiously,\n"
				+ "Her Majesty, Queen Charlotte"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(4), 
				
				"The Queen's Summons: A Regal Affair or a Scandal in the Making?", 
				
				"Rumor has it, Queen Charlotte's summons for a tea gathering at Buckingham Palace Gardens has caused quite the stir amongst the ton. While the invitation exudes regal charm, one cannot help but wonder about the hidden agendas amidst the delicate china and dainty finger sandwiches.\n"
				+ "\n"
				+ "Attendees are advised to proceed with caution, for beneath the facade of refined elegance lies a web of intrigue. Will this gathering be a mere showcase of social graces, or shall it unveil the secrets that lurk behind closed doors?\n"
				+ "\n"
				+ "As always, dear readers, I shall be watching closely and reporting on all the scandalous details that unfold.\n"
				+ "\n"
				+ "Yours in curiosity,"
				+ "Lady Whistledown"
				));

		bulletinBoard.add(new BulletinPost(members.get(7), 
				
				"Embrace Your Independence: A Call to Enlightened Minds", 
				
				"Dear Readers,\n"
				+ "\n"
				+ "Are you tired of the stifling expectations of high society? Do you long for intellectual stimulation beyond the confines of corsets and formal balls? Join me, Eloise Bridgerton, in breaking free from the shackles of tradition and exploring the world of knowledge and empowerment.\n"
				+ "\n"
				+ "Let us gather together to discuss literature, politics, and the pursuit of our own ambitions. Whether you seek enlightenment through books or wish to engage in lively debates on matters of importance, this is the place for you. Let's challenge the status quo and forge our own paths to fulfillment.\n"
				+ "\n"
				+ "With determination and independence,\n"
				+ "Eloise Bridgerton"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(20), 
				
				"Greetings, esteemed members of the ton", 
				
				"As the lady of the house, I bid you all a warm welcome to our virtual gathering place. It is my pleasure to extend an invitation to join me in discussions about all matters concerning the management and upkeep of our esteemed households. Whether it be tips on maintaining a pristine parlour, recipes for delectable desserts, or advice on managing the household staff, let us come together to share our knowledge and experiences.\n"
				+ "\n"
				+ "I look forward to engaging with each of you and fostering a sense of camaraderie among us all.\n"
				+ "\n"
				+ "Yours faithfully,\n"
				+ "Wilson, Housekeeper for the Esteemed Bridgertons"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(4), 
				
				"Rise, Resilient Roses: A Call to Female Fortitude", 
				
				"My dearest readers,\n"
				+ "\n"
				+ "In a world where expectations seek to confine and norms endeavor to define, it is the resilience of women that shines brightest. We are the architects of our destinies, the champions of our dreams, and the authors of our own narratives.\n"
				+ "\n"
				+ "Let us cast aside the shackles of societal constraints and embrace the boundless potential that lies within each of us. Let us celebrate our victories, no matter how small, and draw strength from the challenges that seek to test our resolve.\n"
				+ "\n"
				+ "For it is in adversity that our true power is revealed, and it is through unity that our voices echo loudest. Together, we stand as an indomitable force, shaping the world in our image and paving the way for generations yet to come.\n"
				+ "\n"
				+ "So, my fellow roses, let us bloom boldly and unapologetically, for it is in our collective brilliance that we find our greatest strength.\n"
				+ "\n"
				+ "Yours in solidarity and sisterhood,\n"
				+ "Lady Whistledown"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(3), 
				
				"Musings of a Matriarch", 
				
				"Ah, my dear readers,\n"
				+ "\n"
				+ "It seems the young debutantes of our day are more preoccupied with the latest scandal than with matters of substance. Oh, to see them flit about like butterflies, unaware of the weighty responsibilities that await them beyond the ballroom's gilded walls.\n"
				+ "\n"
				+ "And let us not forget the matchmaking mamas, whose fervent attempts to secure advantageous alliances rival even the most ardent suitor's pursuit. Truly, the lengths to which some will go in the name of marital machinations would make even the most seasoned strategist blush.\n"
				+ "\n"
				+ "But amidst the folly and frivolity, there are glimmers of hope—bright, shining beacons of intelligence, wit, and independence. To these steadfast souls, I raise my glass and offer a nod of admiration, for they are the true architects of our future.\n"
				+ "\n"
				+ "So, my dear readers, let us not be swayed by the trappings of society's expectations. Instead, let us forge our own path, guided by wisdom, grace, and a healthy dose of irreverence.\n"
				+ "\n"
				+ "Yours in wry observation,\n"
				+ "Lady Danbury"
				));
		
		bulletinService.saveAll(bulletinBoard);
		
	}

}

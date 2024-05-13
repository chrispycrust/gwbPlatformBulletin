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
	
	private ApplicationContext context;
	MemberRepository memberRepo;
	MemberService memberService;
	BulletinPostRepository bulletinRepo;
	BulletinPostService bulletinService;
	
	@Autowired
	public Runner(ApplicationContext context, 
			MemberRepository memberRepo, MemberService memberService, 
			BulletinPostRepository bulletinRepo, BulletinPostService bulletinService) {
		
		this.context = context;
		this.memberService = memberService;
		this.bulletinRepo = bulletinRepo;
		this.bulletinService = bulletinService;
		
	}

	@Override
	@Transactional
	public void run(ApplicationArguments args) throws Exception {
		
		// ------------------------------------
		//	DATA LOADERS
		// ------------------------------------
		
		// ------------------------------------
		//	create dummy members
		// ------------------------------------
		
		List<Member> members = new ArrayList<>();
		members.add(new Member("Miss", "Eilish", "Jackson", "eilishJackson@email.com", "eilishJackson123", new ArrayList<>()));
		members.add(new Member("Ms", "Vanessa", "A", "itsMeVanessa@email.com", "vanessaA123", new ArrayList<>()));
		members.add(new Member("Her Majesty", "Charlotte", "of Mecklenburg-Strelitz", "queenBee@royalmail.com", "CrownJewels1772", new ArrayList<>()));
		members.add(new Member("Dowager", "Agatha", "Danbury", "ladyDanbury@tonTimes.com", "PheasantsandSpices1811", new ArrayList<>()));
		members.add(new Member("Lady", "", "Whistledown", "secretScribe@tonTimes.com", "InkAndIntrigue1799", new ArrayList<>()));
		members.add(new Member("Dowager Viscountess", "Violet", "Bridgerton", "matriarchMatch@bridgerton.biz", "FamilyFirst1803", new ArrayList<>()));
		members.add(new Member("Duchess", "Daphne", "Basset", "duchessD@bridgerton.biz", "TrueLove1813", new ArrayList<>()));
		members.add(new Member("The Honourable", "Eloise", "Bridgerton", "BooksBeforeBeaux@bridgerton.biz", "123", new ArrayList<>()));
		members.add(new Member("Ms", "Mary", "Sharma", "mysticMary@sharmaFamily.com", "FamilyFortress1812", new ArrayList<>()));
		members.add(new Member("Viscountess", "Kate", "Bridgerton", "steeledKate@sharmaFamily.com", "IronWill1814", new ArrayList<>()));
		members.add(new Member("Miss", "Edwina", "Sharma", "eruditeEdwina@sharmaFamily.com", "BookishBrilliance1812", new ArrayList<>()));
		members.add(new Member("Mrs", "Alice", "Mondrich", "artfulAlice@mondrichBar.com", "MondrichMuse1815", new ArrayList<>()));
		members.add(new Member("Dowager Lady", "Portia", "Featherington", "proudPortia@feathernet.com", "PrudentPortia1809", new ArrayList<>()));
		members.add(new Member("Miss", "Penelope", "Featherington", "penelopeF@feathernet.com", "WallflowerWit1811", new ArrayList<>()));
		members.add(new Member("Miss", "Siena", "Rosso", "SienaR@operaElite.com", "AriaAdmirer1808", new ArrayList<>()));
		members.add(new Member("Lady", "Marina", "Crane", "MarinaC@seasideSentiments.com", "SeaBreezeSentiments1816", new ArrayList<>()));
		members.add(new Member("Madame", "Genevieve", "Delacroix", "MadameD@delacroixDesigns.com", "DressmakerDelight1806", new ArrayList<>()));
		members.add(new Member("Miss", "Cressida", "Cowper", "CressidaCowper@highCourt.com", "CourtlyCunning1811", new ArrayList<>()));
		members.add(new Member("Miss", "Rose", "Nolan", "roseGarden@nolanNurture.com", "BloomingBelle1804", new ArrayList<>()));
		members.add(new Member("Mrs", "", "Varley", "vivaciousVarley@varleyVentures.com", "VarleyVigilant1801", new ArrayList<>()));
		members.add(new Member("Mrs", "", "Wilson", "wiseWilson@wilsonWit.com", "WittyWisdom1798", new ArrayList<>()));
		members.add(new Member("Miss", "Prudence", "Featherington", "prudenceF@feathernet.com", "WittyWisdom1798", new ArrayList<>()));
		
		memberService.saveAll(members);
		
		// ------------------------------------
		// create dummy posts
		// ------------------------------------
		
		List<BulletinPost> bulletinBoard = new ArrayList<>();
		
		bulletinBoard.add(new BulletinPost(members.get(0), 
				
				"Welcome!", 
				
				"Attention all luminaries! Welcome to the Lounge - a community where we forge strong connections with kindred spirits.\n"
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
				
		bulletinBoard.add(new BulletinPost(members.get(20), 
				
				"Greetings, esteemed members of the ton", 
				
				"As the lady of the house, I bid you all a warm welcome to our virtual gathering place. It is my pleasure to extend an invitation to join me in discussions about all matters concerning the management and upkeep of our esteemed households. Whether it be tips on maintaining a pristine parlour, recipes for delectable desserts, or advice on managing the household staff, let us come together to share our knowledge and experiences.\n"
				+ "\n"
				+ "I look forward to engaging with each of you and fostering a sense of camaraderie among us all.\n"
				+ "\n"
				+ "Yours faithfully,\n"
				+ "Wilson, Housekeeper for the Esteemed Bridgertons"
				));
		
//		bulletinBoard.add(new BulletinPost(members.get(5), 
//				
//				"A Gentle Invitation to New Beginnings\n",
//				
//				"Dear Esteemed Friends,"
//				+ "\n"
//				+ "As seasons change and gardens begin to bloom anew, I find myself reflecting on the natural cycles of renewal and rebirth. Just as a garden left dormant through the winter revives with the warmth of spring, so too can our hearts find new life after seasons of solitude and reflection.\n"
//				+ "\n"
//				+ "To those who have walked a similar path of loss and remembrance, perhaps you too have felt the first stirrings of renewal within your spirit—a desire to once again embrace the joys that companionship can bring. It is with this thought in mind that I propose a discreet gathering, one that we might whimsically call the “Society of Reviving Hearts.”\n"
//				+ "\n"
//				+ "This would be a place for us to share, in hushed tones and with the utmost respect for our pasts, how we might cultivate our emotional landscapes to once again flourish. Whether through quiet conversations, shared readings, or simply enjoying the presence of understanding company, let us explore together how the heart, much like our beloved gardens, might bloom once more with the gentle care of time and attention.\n"
//				+ "\n"
//				+ "If you feel a whisper of readiness to explore the possibility of spring after a long winter, do reach out. Let us walk together with gentle steps towards perhaps, a brighter season.\n"
//				+ "\n"
//				+ "Yours in hope and healing,\n"
//				));
		
		bulletinBoard.add(new BulletinPost(members.get(6), 
				
				"The Joys and Challenges of Motherhood", 
				
				"Dear Fellow Mothers and Caregivers,\n"
				+ "\n"
				+ "As I navigate the rewarding yet demanding journey of motherhood, I find myself continually learning and growing alongside my children. Motherhood has brought with it an abundance of joy, moments of self-doubt, and invaluable lessons about love and patience.\n"
				+ "\n"
				+ "Balancing the duties of a duchess and a mother can often seem like a daunting task, but it is one that has taught me more about myself and my strengths than I could have ever imagined. It has deepened my empathy, honed my multitasking skills, and reshaped my priorities to ensure that while I am fully present in my professional duties, I am first and foremost there for my family.\n"
				+ "\n"
				+ "I would love to hear from other mothers about how you manage the delicate balance between your personal aspirations and the demands of parenthood. What strategies have you found effective? How has motherhood impacted your personal and professional growth?\n"
				+ "\n"
				+ "With warmest regards and heartfelt empathy,\n"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(7), 
				
				"An Invitation to Cultivate More Than Just Flowers", 
				
				"Dear Esteemed Companions,\n"
				+ "\n"
				+ "Are you, like myself, ever curious about the parallels between our delicate botanical hobbies and the broader tapestries of life? If so, allow me to extend an invitation to what we might affectionately call the “Society of Gentle Gardeners.”\n"
				+ "\n"
				+ "This society, under the guise of discussing the finer points of horticulture, will serve as a forum for those among us who appreciate the deeper meanings found in the gentle tending of our gardens. Our conversations might ostensibly revolve around floral arrangements and seasonal planting, yet I trust they will naturally blossom into discussions that touch upon the very essence of our lives and the structures that define them.\n"
				+ "\n"
				+ "If you find yourself intrigued by the notion of intertwining nature’s lessons with life’s larger questions, I encourage you to discreetly express your interest. Together, we shall share in the cultivation of our gardens and perhaps, quite inadvertently, nurture the seeds of broader contemplation and understanding.\n"
				+ "\n"
				+ "Yours in earnest curiosity and reflection,"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(16), 
				
				"Masterclass in Textile Artistry", 
				
				"To All Aspiring Designers and Fabric Enthusiasts,\n"
				+ "\n"
				+ "Join me for a captivating masterclass where I will unveil the secrets of textile selection and the art of dressmaking that have defined my career. This workshop is designed for those who wish to refine their craft and elevate their design skills to the highest echelons of haute couture.\n"
				+ "\n"
				+ "Spaces are limited, as I aim to provide personalized guidance. Register through this platform to secure your place in what promises to be a transformative educational experience.\n"
				+ "\n"
				+ "Discover the artistry behind the fashion,"
				));

		bulletinService.saveAll(bulletinBoard);
		
	}

}

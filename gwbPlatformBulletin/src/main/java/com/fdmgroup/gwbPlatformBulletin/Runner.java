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
		members.add(new Member("The Honourable", "Eloise", "Bridgerton", "rebelReader@literaryladies.com", "BooksBeforeBeaux1805", new ArrayList<>()));
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

		bulletinBoard.add(new BulletinPost(members.get(7), 
				
				"The Subtle Art of Flower Arranging", 
				
				"Dear Esteemed Members,\n"
				+ "\n"
				+ "Today, I find myself contemplating the seemingly simple act of arranging flowers—a task that, upon closer inspection, reveals layers of complexity akin to the very nature of our own journeys toward personal growth and enlightenment.\n"
				+ "\n"
				+ "Consider, if you will, the delicate process of selecting each bloom. Not unlike our own choices in education and associations, each flower adds a unique value to the composition, suggesting that diversity in knowledge and company enhances the beauty and effectiveness of our collective endeavors. How crucial it is, then, to choose with care and purpose, ensuring a well-rounded and vibrant bouquet—much like a well-rounded character.\n"
				+ "\n"
				+ "Furthermore, the structure of our arrangements can mirror the organization of our lives. A strong foundation allows for the most exquisite and ambitious designs to take form without the fear of collapse. In this metaphor, one might see the importance of a solid educational grounding, supporting the flourishing of ambitious personal and professional goals.\n"
				+ "\n"
				+ "Let us discuss, then, how the principles of art and nature might guide our decisions and aspirations. Have you found other everyday activities that resonate with deeper truths about our paths and pursuits? I look forward to your reflections and insights, as we continue to arrange our lives with the same care and strategic thought as our most cherished bouquets.\n"
				+ "\n"
				+ "Yours in thoughtful contemplation,"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(16), 
				
				"Unveiling the Latest in High Fashion", 
				
				"Dear Esteemed Patrons and Fashion Aficionados,\n"
				+ "\n"
				+ "I am thrilled to announce the launch of my latest collection, \"Regency Reimagined,\" inspired by the elegance of the past and designed for the modern woman. Each piece blends timeless grace with contemporary boldness, ensuring that every wearer feels both classic and on the cutting edge.\n"
				+ "\n"
				+ "For a personal viewing and bespoke fitting, I invite you to visit my atelier. Book your appointment through this platform and be the first to don the trends of tomorrow, today. Indulge in the luxury of custom couture that speaks to both your style and stature.\n"
				+ "\n"
				+ "Embrace your unique elegance,"
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
		
		bulletinBoard.add(new BulletinPost(members.get(5), 
				
				"A Gentle Invitation to New Beginnings\n",
				
				"Dear Esteemed Friends,"
				+ "As seasons change and gardens begin to bloom anew, I find myself reflecting on the natural cycles of renewal and rebirth. Just as a garden left dormant through the winter revives with the warmth of spring, so too can our hearts find new life after seasons of solitude and reflection.\n"
				+ "\n"
				+ "To those who have walked a similar path of loss and remembrance, perhaps you too have felt the first stirrings of renewal within your spirit—a desire to once again embrace the joys that companionship can bring. It is with this thought in mind that I propose a discreet gathering, one that we might whimsically call the “Society of Reviving Hearts.”\n"
				+ "\n"
				+ "This would be a place for us to share, in hushed tones and with the utmost respect for our pasts, how we might cultivate our emotional landscapes to once again flourish. Whether through quiet conversations, shared readings, or simply enjoying the presence of understanding company, let us explore together how the heart, much like our beloved gardens, might bloom once more with the gentle care of time and attention.\n"
				+ "\n"
				+ "If you feel a whisper of readiness to explore the possibility of spring after a long winter, do reach out. Let us walk together with gentle steps towards perhaps, a brighter season.\n"
				+ "\n"
				+ "Yours in hope and healing,\n"
				));
		
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
				
				"A Call to Arms: Petals and Stems", 
				
				"Dear Conspirators of the Garden,\n"
				+ "\n"
				+ "Are you, like me, growing weary of the typical chatter about roses and daisies? Do you find that discussing petals and stems could be a revolutionary act, if only we dared to dig a little deeper? Let's turn our seemingly innocent hobby into a battlefield for the mind.\n"
				+ "\n"
				+ "I propose we form a clandestine collective—\"League of Extraordinary Gardeners.\" This will be no ordinary gathering to debate the merits of sun vs. shade. No, we shall use our discussions as a cover to challenge the entrenched norms hidden behind each perfect arrangement. Our tools? Nothing more dangerous than trowels and wit.\n"
				+ "\n"
				+ "If you've ever smirked at the idea of using a discussion on fertiliser to subtly uproot societal conventions, or if you’ve imagined strategising over plant formations as if plotting a quiet coup against the age-old structures of our realm, then you, my friend, belong with us.\n"
				+ "\n"
				+ "Send a discreet sign of your interest—perhaps suggest an unusual plant for our next gathering. Let us cultivate not just flowers, but the seeds of change, sowing ideas as we prune the outdated.\n"
				+ "\n"
				+ "Yours in mischief and (garden) variety,"
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
		
		bulletinBoard.add(new BulletinPost(members.get(21), 
				
				"Delighted to Share My Recent Accomplishments", 
				
				"Dear Friends and Esteemed Acquaintances,\n"
				+ "\n"
				+ "I am thrilled to share with you some exciting news about my recent endeavors, which have been both challenging and incredibly rewarding. I had the distinct pleasure of organizing the annual Spring Charity Gala last month, an event that was not only a resounding success but also raised significant funds for a cause close to our hearts.\n"
				+ "\n"
				+ "The gala was attended by notable figures from various sectors, and I was honored to receive commendations for my role in ensuring the evening was both enchanting and effective. It was a wonderful opportunity to blend social engagement with meaningful impact, and I am eager to explore further avenues where I can contribute my organizational skills and passion for the finer things in life.\n"
				+ "\n"
				+ "Please feel free to reach out if you wish to discuss potential collaborations or if you're curious about the behind-the-scenes magic that goes into organizing such splendid events!\n"
				+ "\n"
				+ "Yours with refined anticipation,"
				));
		
		bulletinBoard.add(new BulletinPost(members.get(16), 
				
				"Exclusive Offer for First-Time Clients", 
				
				"Greetings to All Modern Connoisseurs of Style,\n"
				+ "\n"
				+ "For those yet to experience the magic of bespoke tailoring, I extend a warm invitation to explore your fashion fantasies with an exclusive offer: 20% off your first custom creation. Let us embark on a journey of transformation together, where your desires are woven into every thread of your garment.\n"
				+ "\n"
				+ "Seize this opportunity to enhance your wardrobe with attire that is crafted just for you. Schedule your consultation today, and step into a world where fashion meets personal expression.\n"
				+ "\n"
				+ "Your couture adventure awaits,"
				));
		
		bulletinService.saveAll(bulletinBoard);
		
	}

}

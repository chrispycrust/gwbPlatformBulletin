package com.fdmgroup.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.fdmgroup.gwbPlatformBulletin.dal.MemberRepository;

class MemberServiceTest {
	
	// mock userRespository

	@RunWith(MockitoJUnitRunner.class)
	@SpringBootTest(webEnvironment = WebEnvironment .NONE)
	public class ContactsManagementServiceUnitTest {
	
	@Mock
	private MemberRepository memberRepository;
	
	@InjectMocks
	private ContactsManagementService contactsManagementService;
	
	@Before
	public void setup) {
		MockitoAnnotations.nitMocks(this);
	}
	}
	
	@Test
	public void testGetAllMembers {
	  
	  // Arrange
//	  CustomerContact aMockContact = new CustomerContact;
//	  aMockContact.setFirstName("Jenny");
//	  aMockContact. setLastName("Johnson");
	  
//	  when(customerContactRepository.save(any(CustomerContact.class))).thenReturn(aMockContact);
	  
	  // Act
//	  CustomerContact newContact = contactsManagementService.add(null);
	  
	  // Assert
//	  assertEquals("Jenny", newContact).getFirstName());
	  
	}

}

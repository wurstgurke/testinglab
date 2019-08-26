describe('spt-membership-tests', () => {
  it('logs in as a admin into membership and logs out again', () => {
    cy.visit('http://membership-fe-nspmvdev.nspmv.intern.neusta.de/login')
    cy.get('#username').type('membership_admin')
    cy.get('#password').type('1234')
    cy.contains('einloggen').click()
    cy.contains('Logout').click()
  })
})

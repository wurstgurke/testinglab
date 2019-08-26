describe('spt-membership-tests', () => {
  it('logs in as a admin into membership', () => {
    cy.visit('http://membership-fe-nspmvdev.nspmv.intern.neusta.de/login')
    cy.get('#username').type('membership_admin')
    cy.get('#password').type('1234')
    cy.contains('einloggen').click()
  })
})

import React from 'react'

const Header = ({ toggleModal, numOfContacts }) => {
  return (
    <header className = 'header'>
        <div className='container'>
            <h2> Contact List ({numOfContacts}) </h2>
            <button className='btn' onClick={() => toggleModal(true)}>
                <i className="bi bi-plus-square"> Add New Contact </i>
            </button>
        </div>
    </header>
  )
}

export default Header
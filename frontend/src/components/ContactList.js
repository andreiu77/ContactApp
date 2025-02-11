import React from 'react'
import Contact from './Contact'

const ContactList = ({ data, currPage, getContacts }) => {
  return (
    <main className='main'>
        {data?.content?.length === 0 && <div> No contacts </div>}

        <ul className='contact__list'>
            {data?.content?.length > 0 && data.content.map(contact => <Contact contact = {contact} key = {contact.id}/>)}
        </ul>

        {data?.content?.length > 0 && data?.totalPages > 1 && 
        <div className='pagination'>
            <a onClick={() => getContacts(currPage - 1)} className={currPage === 0 ? 'disable' : ''}> &laquo; </a>
            
            { data && [...Array(data.totalPages).keys()].map((page, index) =>
                <a onClick={() => getContacts(page)} className={ currPage === page ? 'active' : ''} key = {page}> {page + 1} </a>) }
            
            <a onClick={() => getContacts(currPage + 1)} className={currPage === data.totalPages - 1 ? 'disable' : ''}> &raquo; </a>
        </div>
        }

    </main>
  )
}

export default ContactList